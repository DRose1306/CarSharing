package com.example.carsharing.dto;

import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.User;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class TripCreateDto implements Serializable {
    String startTime;
    String endTime;
    String userId;
    String carId;
}