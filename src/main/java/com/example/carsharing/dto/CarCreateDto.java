package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.CarBrand;
import lombok.Value;

@Value
public class CarCreateDto {
    String yearOfRelease;
    String licensePlate;
    CarBrand brand;
}