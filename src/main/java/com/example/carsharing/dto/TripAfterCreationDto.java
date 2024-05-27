package com.example.carsharing.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TripAfterCreationDto {
    private BigDecimal cost;
    private double distance;
    private String user;
    private String car;

}