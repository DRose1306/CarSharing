package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.CarBrand;
import lombok.Value;

import java.io.Serializable;

@Value
public class CarCreateDto implements Serializable {
    String yearOfRelease;
    String licensePlate;
    CarBrand brand;
}