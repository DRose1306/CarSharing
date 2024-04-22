package com.example.carsharing.service;

import com.example.carsharing.entity.Trip;

import java.util.UUID;

public interface TripService {
    Trip getTripById(UUID id);
    void deleteTripById(UUID id);
    Trip createTrip(Trip trip);
    Trip updateTripById(UUID id, Trip updatedTrip);
}
