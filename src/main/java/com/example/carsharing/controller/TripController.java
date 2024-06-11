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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller class responsible for managing trip-related HTTP requests.
 */
@Validated
@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    /**
     * Retrieves trip information by its ID.
     *
     * @param id The ID of the trip to retrieve.
     * @return The trip object.
     */
    @GetTrip(path = "/get/{id}")
    public Trip getTripById(@PathVariable("id") @UuidFormatChecker String id) {
        return tripService.getTripById(UUID.fromString(id));
    }

    /**
     * Deletes a trip by its ID.
     *
     * @param id The ID of the trip to delete.
     * @return A message indicating the success of the operation.
     */
    @DeleteTrip(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTripById(@PathVariable @UuidFormatChecker String id) {
        return tripService.deleteTripById(UUID.fromString(id));
    }

    /**
     * Creates a new trip.
     *
     * @param tripCreateDto The DTO containing the information for creating the new trip.
     * @return The DTO containing information about the newly created trip.
     */
    @CreateTrip(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TripAfterCreationDto createTrip(@RequestBody TripCreateDto tripCreateDto) {
        return tripService.createTrip(tripCreateDto);
    }

    /**
     * Updates trip information.
     *
     * @param id            The ID of the trip to update.
     * @param tripCreateDto The DTO containing the updated trip information.
     * @return The updated trip object.
     */
    @UpdateTrip(path = "/update/{id}")
    public Trip updateTrip(@PathVariable @UuidFormatChecker String id, @RequestBody TripCreateDto tripCreateDto) {
        return tripService.updateTripById(UUID.fromString(id), tripCreateDto);
    }
}
