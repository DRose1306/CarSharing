package com.example.carsharing.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO representing trip details after creation.
 */
@Data
public class TripAfterCreationDto {

    /**
     * The cost of the trip.
     */
    private BigDecimal cost;

    /**
     * The distance traveled during the trip.
     */
    private double distance;

    /**
     * The user associated with the trip.
     */
    private String user;

    /**
     * The car used for the trip.
     */
    private String car;

}
