package com.example.carsharing.repository;

import com.example.carsharing.entity.Trip;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {
    Trip findByStartTimeAndUser_UserId(LocalDateTime startTime, UUID userID);
    Trip getTripByTripId(@Nonnull UUID id);
}
