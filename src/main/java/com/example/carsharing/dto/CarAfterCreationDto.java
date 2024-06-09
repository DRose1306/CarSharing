package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.CarStatus;
import lombok.Data;

/**
 * DTO for representing data about a created car after the creation operation.
 */
@Data
public class CarAfterCreationDto {
    /**
     * Message about the successful creation of the car.
     */
    private String message;

    /**
     * License plate of the car.
     */
    private String licensePlate;

    /**
     * Brand of the car.
     */
    private String brand;

    /**
     * Status of the car.
     */
    private CarStatus status;

    /**
     * Date and time of the car's creation.
     */
    private String createdAt;
}
