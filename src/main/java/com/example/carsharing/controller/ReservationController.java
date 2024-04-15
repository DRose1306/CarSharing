package com.example.carsharing.controller;

import com.example.carsharing.entity.Reservation;
import com.example.carsharing.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/get/{id}")
    public Reservation getReservationById(@PathVariable("id") UUID id) {
        return reservationService.getReservationById(id);
    }
}
