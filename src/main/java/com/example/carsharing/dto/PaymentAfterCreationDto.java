package com.example.carsharing.dto;

import lombok.Data;

@Data
public class PaymentAfterCreationDto {
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String user;
    private boolean status;
}