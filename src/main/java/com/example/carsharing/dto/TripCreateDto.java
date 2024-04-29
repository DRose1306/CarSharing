package com.example.carsharing.dto;


import lombok.Value;
import java.io.Serializable;


@Value
public class TripCreateDto implements Serializable {
    String startTime;
    String endTime;
    String userId;
    String carId;
}