package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.PaymentMethod;
import lombok.Data;

@Data
public class PaymentCreateDto  {
    double amount;
    String paymentDate;
    PaymentMethod paymentMethod;
    String userId;
}