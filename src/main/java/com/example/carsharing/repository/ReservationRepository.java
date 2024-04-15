package com.example.carsharing.repository;

import com.example.carsharing.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Reservation getReservationByReservationId(UUID id);
}
