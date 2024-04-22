package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Payment;
import com.example.carsharing.exception.PaymentNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.repository.PaymentRepository;
import com.example.carsharing.service.PaymentService;
import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public void deletePaymentById(UUID id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) {
            throw new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST);
        }
        paymentRepository.deletePaymentByPaymentId(id);
    }

    @Override
    @Transactional
    public Payment createPayment(Payment payment) {
        return paymentRepository.saveAndFlush(payment);
    }

    @Override
    @Transactional
    public Payment updatePaymentById(UUID id, Payment updatedPayment) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) {
            throw new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST);
        }
        payment.setAmount(updatedPayment.getAmount());
        payment.setPaymentDate(updatedPayment.getPaymentDate());
        payment.setPaymentMethod(updatedPayment.getPaymentMethod());
        payment.setStatus(updatedPayment.isStatus());
        payment.setUser(updatedPayment.getUser());

        return paymentRepository.saveAndFlush(payment);
    }
}
