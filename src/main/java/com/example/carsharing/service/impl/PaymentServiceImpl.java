package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Payment;
import com.example.carsharing.exception.PaymentNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.repository.PaymentRepository;
import com.example.carsharing.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment getPaymentById(UUID id) {
        Payment payment = paymentRepository.getPaymentByPaymentId(id);
        if (payment == null) {
            throw new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST);
        }
        return payment;
    }
}
