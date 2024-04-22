package com.example.carsharing.service;

import com.example.carsharing.entity.Payment;

import java.util.UUID;

public interface PaymentService {
    Payment getPaymentById(UUID id);
    void deletePaymentById(UUID id);
    Payment createPayment(Payment payment);
    Payment updatePaymentById(UUID id, Payment updatedPayment);
}
