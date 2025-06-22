package com.example.deliverymatch.controllers;

import com.example.deliverymatch.Services.RequestService;
import com.example.deliverymatch.dtos.RequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@CrossOrigin("*")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PreAuthorize("hasRole('SENDER')")
    @PostMapping
    public ResponseEntity<RequestDTO> createRequest(@Valid @RequestBody RequestDTO requestDTO) {
        RequestDTO createdRequest = requestService.createRequest(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PreAuthorize("hasAnyRole('SENDER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<RequestDTO>> getAllRequests() {
        List<RequestDTO> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @PreAuthorize("hasRole('SENDER')")
    @GetMapping("/senderRequests")
    public ResponseEntity<List<RequestDTO>> getSenderRequests() {
        List<RequestDTO> requests = requestService.getSenderRequests();
        return ResponseEntity.ok(requests);
    }

    @PreAuthorize("hasAnyRole('SENDER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<RequestDTO> getRequestById(@PathVariable Long id) {
        RequestDTO request = requestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/requestsByAnnouncementId/{id}")
    public ResponseEntity<List<RequestDTO>> getRequestsByAnnouncementId(@PathVariable Long id) {
        List<RequestDTO> requests = requestService.getAllRequestsByAnnouncementId(id);
        return ResponseEntity.ok(requests);
    }

    @PreAuthorize("hasRole('SENDER')")
    @PutMapping("/{id}")
    public ResponseEntity<RequestDTO> updateRequest(
            @PathVariable Long id,
            @RequestBody RequestDTO requestDTO) {
        RequestDTO updatedRequest = requestService.updateRequest(id, requestDTO);
        return ResponseEntity.ok(updatedRequest);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<RequestDTO> updateRequestStatus(
            @PathVariable Long id,
            @RequestBody RequestDTO requestDTO) {
        RequestDTO updatedRequest = requestService.updateRequestStatus(id, requestDTO);
        return ResponseEntity.ok(updatedRequest);
    }

    @PreAuthorize("hasAnyRole('SENDER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}