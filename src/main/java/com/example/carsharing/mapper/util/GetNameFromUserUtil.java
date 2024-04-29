package com.example.carsharing.mapper.util;

import com.example.carsharing.entity.Payment;
import com.example.carsharing.entity.Trip;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GetNameFromUserUtil {
    public static String getName(Trip trip){
        return trip.getUser().getFirstName()+" "+trip.getUser().getLastName();
    }

    public static String getName(Payment payment){
        return payment.getUser().getFirstName()+" "+payment.getUser().getLastName();
    }
}
