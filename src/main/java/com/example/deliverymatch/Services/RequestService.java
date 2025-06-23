package com.example.deliverymatch.Services;

import com.example.deliverymatch.dtos.RequestDTO;
import com.example.deliverymatch.entities.*;
import com.example.deliverymatch.mappers.AnnouncementMapper;
import com.example.deliverymatch.mappers.RequestMapper;
import com.example.deliverymatch.repositories.AnnouncementRepository;
import com.example.deliverymatch.repositories.RequestRepository;
import com.example.deliverymatch.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final RequestMapper requestMapper;
    private final AnnouncementRepository announcementRepository;

    public RequestService(RequestRepository requestRepository, UserRepository userRepository, RequestMapper requestMapper, AnnouncementRepository announcementRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.requestMapper = requestMapper;
        this.announcementRepository = announcementRepository;
    }

    @Transactional
    public RequestDTO createRequest(RequestDTO requestDTO) {
        Request request = requestMapper.toEntity(requestDTO);

        if (requestDTO.getAnnouncementId() != null) {
            Announcement announcement = announcementRepository.findById(requestDTO.getAnnouncementId())
                    .orElseThrow(() -> new EntityNotFoundException("Announcement not found"));
            request.setAnnouncement(announcement);
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Sender sender = (Sender) userRepository.findByEmail(email);

        request.setSender(sender);

        Request savedRequest = requestRepository.save(request);
        return requestMapper.toDTO(savedRequest);
    }

    @Transactional(readOnly = true)
    public List<RequestDTO> getAllRequests() {
        return requestMapper.toDTOs(requestRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<RequestDTO> getSenderRequests() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return requestMapper.toDTOs(requestRepository.findAllBySenderEmail(email));
    }

    @Transactional(readOnly = true)
    public RequestDTO getRequestById(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request not found"));
        return requestMapper.toDTO(request);
    }

    @Transactional(readOnly = true)
    public List<RequestDTO> getAllRequestsByAnnouncementId(Long id) {
        return requestMapper.toDTOs(requestRepository.findAllByAnnouncementId(id));
    }

    @Transactional
    public RequestDTO updateRequest(Long id, RequestDTO requestDTO) {
        Request existingRequest = requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request not found"));

        existingRequest.setDimensions(requestDTO.getDimensions());
        existingRequest.setWeight(requestDTO.getWeight());
        existingRequest.setType(requestDTO.getType());

        if (requestDTO.getAnnouncementId() != null &&
                !requestDTO.getAnnouncementId().equals(existingRequest.getAnnouncement().getId())) {
            Announcement announcement = announcementRepository.findById(requestDTO.getAnnouncementId())
                    .orElseThrow(() -> new EntityNotFoundException("Announcement not found"));
            existingRequest.setAnnouncement(announcement);
        }

        Request updatedRequest = requestRepository.save(existingRequest);
        return requestMapper.toDTO(updatedRequest);
    }

    @Transactional
    public RequestDTO updateRequestStatus(Long id, RequestDTO requestDTO) {
        Request existingRequest = requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request not found"));

        existingRequest.setStatus(requestDTO.getStatus());

//        if (requestDTO.getAnnouncementId() != null &&
//                !requestDTO.getAnnouncementId().equals(existingRequest.getAnnouncement().getId())) {
//            Announcement announcement = announcementRepository.findById(requestDTO.getAnnouncementId())
//                    .orElseThrow(() -> new EntityNotFoundException("Announcement not found"));
//            existingRequest.setAnnouncement(announcement);
//        }

        Request updatedRequest = requestRepository.save(existingRequest);
        return requestMapper.toDTO(updatedRequest);
    }

    @Transactional
    public void deleteRequest(Long id) {
        if (!requestRepository.existsById(id)) {
            throw new EntityNotFoundException("Request not found");
        }
        requestRepository.deleteById(id);
    }
}