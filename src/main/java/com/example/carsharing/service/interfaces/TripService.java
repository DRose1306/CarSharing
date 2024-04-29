package com.example.carsharing.service.interfaces;

import com.example.carsharing.dto.TripAfterCreationDto;
import com.example.carsharing.dto.TripCreateDto;
import com.example.carsharing.entity.Trip;

import java.util.UUID;

public interface TripService {
    Trip getTripById(UUID id);
    void deleteTripById(UUID id);
    TripAfterCreationDto createTrip(TripCreateDto tripCreateDto);
    Trip updateTripById(UUID id, TripCreateDto tripCreateDto);
}
