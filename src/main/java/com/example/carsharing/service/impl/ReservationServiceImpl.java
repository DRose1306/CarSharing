package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Reservation;
import com.example.carsharing.exception.ReservationNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
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
        Reservation reservation = reservationRepository.getReservationByReservationId(id);
        if (reservation == null) {
            throw new ReservationNotExistException(ErrorMessage.RESERVATION_NOT_EXIST);
        }
        return reservation;
    }
}
