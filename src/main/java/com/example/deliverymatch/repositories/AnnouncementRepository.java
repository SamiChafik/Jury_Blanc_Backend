package com.example.deliverymatch.repositories;

import com.example.deliverymatch.entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    @Query("SELECT a FROM Announcement a LEFT JOIN FETCH a.driver WHERE a.id = :id")
    Optional<Announcement> findByIdWithDriver(@Param("id") Long id);

    @Query("SELECT a FROM Announcement a LEFT JOIN FETCH a.driver")
    List<Announcement> findAllWithDrivers();
}
