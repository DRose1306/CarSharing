package com.example.carsharing.dto;

import lombok.Data;

/**
 * DTO representing trip creation details.
 */
@Data
public class TripCreateDto {

    /**
     * The start time of the trip.
     */
    String startTime;

    /**
     * The end time of the trip.
     */
    String endTime;

    /**
     * The ID of the user associated with the trip.
     */
    String userId;

    /**
     * The ID of the car used for the trip.
     */
    String carId;
}
