package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.CarBrand;
import lombok.Data;

@Data
public class CarCreateDto {
    String yearOfRelease;
    String licensePlate;
    CarBrand brand;
}