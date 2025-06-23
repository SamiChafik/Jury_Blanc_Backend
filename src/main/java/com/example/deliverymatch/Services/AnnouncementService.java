package com.example.deliverymatch.Services;

import com.example.deliverymatch.dtos.AnnouncementDTO;
import com.example.deliverymatch.entities.Announcement;
import com.example.deliverymatch.entities.Driver;
import com.example.deliverymatch.entities.User;
import com.example.deliverymatch.mappers.AnnouncementMapper;
import com.example.deliverymatch.repositories.AnnouncementRepository;
import com.example.deliverymatch.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final UserRepository userRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper, UserRepository userRepository) {
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public AnnouncementDTO createAnnouncement(AnnouncementDTO announcementDTO) {
        Announcement announcement = announcementMapper.toEntity(announcementDTO);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Driver driver = (Driver) userRepository.findByEmail(email);

        announcement.setDriver(driver);
//        if (announcementDTO.getDriverId() != null) {
//            User driver = userRepository.findById(announcementDTO.getDriverId())
//                    .orElseThrow(() -> new EntityNotFoundException("Driver not found"));
//            announcement.setDriver((Driver) driver);
//        }

        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return announcementMapper.toDTO(savedAnnouncement);
    }

    @Transactional(readOnly = true)
    public List<AnnouncementDTO> getAllAnnouncements() {
        return announcementMapper.toDTOs(announcementRepository.findAllWithDrivers());
    }

    @Transactional(readOnly = true)
    public AnnouncementDTO getAnnouncementById(Long id) {
        Announcement announcement = announcementRepository.findByIdWithDriver(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found with id: " + id));
        return announcementMapper.toDTO(announcement);
    }

    @Transactional
    public AnnouncementDTO updateAnnouncement(Long id, AnnouncementDTO announcementDTO) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found with id: " + id));

        existingAnnouncement.setDeparture(announcementDTO.getDeparture());
        existingAnnouncement.setFinal_destination(announcementDTO.getFinal_destination());
        existingAnnouncement.setStages(announcementDTO.getStages());
        existingAnnouncement.setMaximum_dimensions(announcementDTO.getMaximum_dimensions());
        existingAnnouncement.setGoodsType(announcementDTO.getGoodsType());
        existingAnnouncement.setCapacity(announcementDTO.getCapacity());
        existingAnnouncement.setDate(announcementDTO.getDate());
        existingAnnouncement.setStatus(announcementDTO.getStatus());
        Announcement updatedAnnouncement = announcementRepository.save(existingAnnouncement);
        return announcementMapper.toDTO(updatedAnnouncement);
    }

    @Transactional
    public AnnouncementDTO updateStatus(Long id, AnnouncementDTO announcementDTO) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found with id: " + id));

        existingAnnouncement.setStatus(announcementDTO.getStatus());
        Announcement updatedAnnouncement = announcementRepository.save(existingAnnouncement);
        return announcementMapper.toDTO(updatedAnnouncement);
    }

    @Transactional
    public void deleteAnnouncement(Long id) {
        if (!announcementRepository.existsById(id)) {
            throw new EntityNotFoundException("Announcement not found with id: " + id);
        }
        announcementRepository.deleteById(id);
    }
}