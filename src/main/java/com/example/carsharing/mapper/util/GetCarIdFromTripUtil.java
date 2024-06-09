package com.example.carsharing.mapper.util;

import com.example.carsharing.entity.Trip;
import lombok.experimental.UtilityClass;

/**
 * Utility class for getting the car ID from a trip entity.
 */
@UtilityClass
public class GetCarIdFromTripUtil {

    /**
     * Retrieves the car ID from the given trip entity.
     *
     * @param trip The trip entity.
     * @return The car ID as a string.
     */
    public static String getCarId(Trip trip) {
        return trip.getCar().getCarId().toString();
    }
}
