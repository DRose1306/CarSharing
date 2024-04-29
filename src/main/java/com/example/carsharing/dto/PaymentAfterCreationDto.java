package com.example.carsharing.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class PaymentAfterCreationDto implements Serializable {
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String user;
    private boolean status;
}