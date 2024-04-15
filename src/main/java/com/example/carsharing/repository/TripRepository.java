package com.example.carsharing.repository;

import com.example.carsharing.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    Trip getTripByTripId(UUID id);
}
