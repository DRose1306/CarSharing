package com.example.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentAfterCreationDto implements Serializable {
    private String paymentId;
    private boolean status = true;
}