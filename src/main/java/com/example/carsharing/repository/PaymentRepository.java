package com.example.carsharing.repository;

import com.example.carsharing.entity.Payment;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Payment findByPaymentDateAndUser_UserId(LocalDateTime paymentDate, UUID userId);

    Payment getPaymentByPaymentId(@Nonnull UUID id);
}
