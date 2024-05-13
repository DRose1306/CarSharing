package com.example.carsharing.dto;

import lombok.Data;


@Data
public class TripCreateDto {
    String startTime;
    String endTime;
    String userId;
    String carId;
}