package com.example.carsharing.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class CarAfterCreationDto implements Serializable {
   private String carId;
   private String status = "CAR CREATED";
}