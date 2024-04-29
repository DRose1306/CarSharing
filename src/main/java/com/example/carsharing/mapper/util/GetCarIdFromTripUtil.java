package com.example.carsharing.mapper.util;

import com.example.carsharing.entity.Trip;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class GetCarIdFromTripUtil {
    public static String getCarId(Trip trip){
        return trip.getCar().getCarId().toString();
    }
}
