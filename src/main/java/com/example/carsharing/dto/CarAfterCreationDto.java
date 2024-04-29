package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.CarStatus;
import lombok.Data;

import java.io.Serializable;


@Data
public class CarAfterCreationDto implements Serializable {
   private String message;
   private String licensePlate;
   private String brand;
   private CarStatus status;
   private String createdAt;
}