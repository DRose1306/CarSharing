package com.example.carsharing.repository;

import com.example.carsharing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Payment getPaymentByPaymentId(UUID id);
    void deletePaymentByPaymentId(UUID id);
}
