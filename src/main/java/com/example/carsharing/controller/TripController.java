package com.example.carsharing.controller;

import com.example.carsharing.entity.Trip;
import com.example.carsharing.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @GetMapping("/get/{id}")
    public Trip getTripById(@PathVariable("id") UUID id) {
        return tripService.getTripById(id);
    }

    @DeleteMapping("/delete_trip/{id}")
    public void deleteTripById(@PathVariable UUID id) {
        tripService.deleteTripById(id);
    }

    @PostMapping("/create_trip")
    public Trip createTrip(@RequestBody Trip trip){
        return tripService.createTrip(trip);
    }

    @PutMapping("/update_trip/{id}")
    public Trip updateTrip(@PathVariable UUID id, @RequestBody Trip trip){
        return tripService.updateTripById(id, trip);
    }
}
