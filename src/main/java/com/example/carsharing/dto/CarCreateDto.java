package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.CarBrand;
import lombok.Data;

/**
 * DTO for creating a new car.
 */
@Data
public class CarCreateDto {

    /**
     * The year of release of the car.
     */
    String yearOfRelease;

    /**
     * The license plate of the car.
     */
    String licensePlate;

    /**
     * The brand of the car.
     */
    CarBrand brand;
}
