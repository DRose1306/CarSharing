package com.example.carsharing.controller;

import com.example.carsharing.entity.Trip;
import com.example.carsharing.service.TripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/get/{id}")
    public Trip getTripById(@PathVariable("id") UUID id) {
        return tripService.getTripById(id);
    }
}
