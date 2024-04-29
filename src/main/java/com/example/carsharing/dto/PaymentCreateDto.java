package com.example.carsharing.dto;

import com.example.carsharing.entity.User;
import com.example.carsharing.entity.enums.PaymentMethod;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Value
public class PaymentCreateDto implements Serializable {
    double amount;
    LocalDateTime paymentDate;
    PaymentMethod paymentMethod;
    String userId;
}