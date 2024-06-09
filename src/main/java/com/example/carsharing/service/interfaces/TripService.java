package com.example.carsharing.service.interfaces;

import com.example.carsharing.dto.TripAfterCreationDto;
import com.example.carsharing.dto.TripCreateDto;
import com.example.carsharing.entity.Trip;

import java.util.UUID;

public interface TripService {
    /**
     * Retrieves a trip by its identifier.
     *
     * @param id Trip identifier.
     * @return Trip object.
     */
    Trip getTripById(UUID id);

    /**
     * Deletes a trip by its identifier.
     *
     * @param id Trip identifier.
     * @return A message about the successful deletion of the trip.
     */
    String deleteTripById(UUID id);

    /**
     * Creates a new trip.
     *
     * @param tripCreateDto Data about the trip to be created.
     * @return Data about the created trip.
     */
    TripAfterCreationDto createTrip(TripCreateDto tripCreateDto);

    /**
     * Updates trip information by its identifier.
     *
     * @param id            Trip identifier.
     * @param tripCreateDto Updated trip data.
     * @return Updated trip object.
     */
    Trip updateTripById(UUID id, TripCreateDto tripCreateDto);
}
