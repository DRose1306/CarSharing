package com.example.carsharing.dto;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TripAfterCreationDto implements Serializable {
     private BigDecimal cost;
     private double distance;
     private String user;
     private String car;

}