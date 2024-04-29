package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.PaymentMethod;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PaymentAfterCreationDto implements Serializable {
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String user;
    private boolean status;
}