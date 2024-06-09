package com.example.carsharing.controller;

import com.example.carsharing.annotation.CreateTrip;
import com.example.carsharing.annotation.DeleteTrip;
import com.example.carsharing.annotation.GetTrip;
import com.example.carsharing.annotation.UpdateTrip;
import com.example.carsharing.dto.TripAfterCreationDto;
import com.example.carsharing.dto.TripCreateDto;
import com.example.carsharing.entity.Trip;
import com.example.carsharing.service.interfaces.TripService;
import com.example.carsharing.validation.annotation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    @GetTrip(path = "/get/{id}")
    public Trip getTripById(@PathVariable("id") @UuidFormatChecker String id) {
        return tripService.getTripById(UUID.fromString(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteTrip(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTripById(@PathVariable @UuidFormatChecker String id) {
        return tripService.deleteTripById(UUID.fromString(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @CreateTrip(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TripAfterCreationDto createTrip(@RequestBody TripCreateDto tripCreateDto){
        return tripService.createTrip(tripCreateDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @UpdateTrip(path = "/update/{id}")
    public Trip updateTrip(@PathVariable @UuidFormatChecker String id, @RequestBody TripCreateDto tripCreateDto){
        return tripService.updateTripById(UUID.fromString(id), tripCreateDto);
    }
}
