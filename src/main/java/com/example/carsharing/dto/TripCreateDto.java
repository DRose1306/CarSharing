package com.example.carsharing.dto;


import lombok.Value;


@Value
public class TripCreateDto {
    String startTime;
    String endTime;
    String userId;
    String carId;
}