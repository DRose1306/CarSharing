package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.CarStatus;
import lombok.Data;


@Data
public class CarAfterCreationDto {
   private String message;
   private String licensePlate;
   private String brand;
   private CarStatus status;
   private String createdAt;
}