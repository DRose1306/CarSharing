package com.example.carsharing.mapper.util;

import com.example.carsharing.entity.Payment;
import com.example.carsharing.entity.Trip;
import lombok.experimental.UtilityClass;

/**
 * Utility class for getting the name from user entities.
 */
@UtilityClass
public class GetNameFromUserUtil {

    /**
     * Retrieves the name from the user associated with the given trip entity.
     *
     * @param trip The trip entity.
     * @return The name of the user associated with the trip.
     */
    public static String getName(Trip trip) {
        return trip.getUser().getFirstName() + " " + trip.getUser().getLastName();
    }

    /**
     * Retrieves the name from the user associated with the given payment entity.
     *
     * @param payment The payment entity.
     * @return The name of the user associated with the payment.
     */
    public static String getName(Payment payment) {
        return payment.getUser().getFirstName() + " " + payment.getUser().getLastName();
    }
}
