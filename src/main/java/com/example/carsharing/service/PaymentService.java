package com.example.carsharing.service;

import com.example.carsharing.dto.PaymentAfterCreationDto;
import com.example.carsharing.dto.PaymentCreateDto;
import com.example.carsharing.entity.Payment;

import java.util.UUID;

public interface PaymentService {
    Payment getPaymentById(UUID id);
    void deletePaymentById(UUID id);
    PaymentAfterCreationDto createPayment(PaymentCreateDto paymentCreateDto);
    Payment updatePaymentById(UUID id, Payment updatedPayment);
}
