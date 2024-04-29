package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.CarBrand;
import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;

@Value
public class CarCreateDto implements Serializable {
    String yearOfRelease;
    String licensePlate;
    CarBrand brand;
}