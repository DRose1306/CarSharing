package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Reservation;
import com.example.carsharing.repository.ReservationRepository;
import com.example.carsharing.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    @Override
    public Reservation getReservationById(UUID id) {
        return reservationRepository.getReservationByReservationId(id);
    }
}
