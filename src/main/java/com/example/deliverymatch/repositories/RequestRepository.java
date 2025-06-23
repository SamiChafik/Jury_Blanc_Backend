package com.example.deliverymatch.repositories;

import com.example.deliverymatch.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllBySenderEmail(String senderEmail);

    List<Request> findAllByAnnouncementId(Long id);
}
