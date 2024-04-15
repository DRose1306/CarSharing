package com.example.carsharing.service;

import com.example.carsharing.entity.Payment;

import java.util.UUID;

public interface PaymentService {
    Payment getPaymentById(UUID id);
}
