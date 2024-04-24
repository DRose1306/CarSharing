package com.example.carsharing.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class TripCreateDto implements Serializable {
    LocalDateTime startTime;
    LocalDateTime endTime;
    double distance;
    BigDecimal cost;
    UserCreateDto user;
    CarCreateDto car;
}