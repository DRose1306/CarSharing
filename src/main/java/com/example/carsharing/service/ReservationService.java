package com.example.carsharing.service;

import com.example.carsharing.entity.Reservation;

import java.util.UUID;

public interface ReservationService {
    Reservation getReservationById(UUID id);
}
