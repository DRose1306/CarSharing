package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.PaymentMethod;
import lombok.Value;
import java.time.LocalDateTime;

@Value
public class PaymentCreateDto  {
    double amount;
    LocalDateTime paymentDate;
    PaymentMethod paymentMethod;
    String userId;
}