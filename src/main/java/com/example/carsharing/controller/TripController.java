package com.example.carsharing.controller;

import com.example.carsharing.annotation.CreateTrip;
import com.example.carsharing.annotation.DeleteTrip;
import com.example.carsharing.annotation.GetTrip;
import com.example.carsharing.annotation.UpdateTrip;
import com.example.carsharing.dto.TripAfterCreationDto;
import com.example.carsharing.dto.TripCreateDto;
import com.example.carsharing.entity.Trip;
import com.example.carsharing.service.interfaces.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @GetTrip(path = "/get/{id}")
    public Trip getTripById(@PathVariable("id") UUID id) {
        return tripService.getTripById(id);
    }

    @DeleteTrip(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTripById(@PathVariable UUID id) {
        return tripService.deleteTripById(id);
    }

    @CreateTrip(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TripAfterCreationDto createTrip(@RequestBody TripCreateDto tripCreateDto){
        return tripService.createTrip(tripCreateDto);
    }

    @UpdateTrip(path = "/update/{id}")
    public Trip updateTrip(@PathVariable UUID id, @RequestBody TripCreateDto tripCreateDto){
        return tripService.updateTripById(id, tripCreateDto);
    }
}
