package com.example.carsharing.service.interfaces;

import com.example.carsharing.dto.PaymentAfterCreationDto;
import com.example.carsharing.dto.PaymentCreateDto;
import com.example.carsharing.entity.Payment;

import java.util.UUID;

public interface PaymentService {
    /**
     * Retrieves a payment by its identifier.
     *
     * @param id Payment identifier.
     * @return Payment object.
     */
    Payment getPaymentById(UUID id);

    /**
     * Deletes a payment by its identifier.
     *
     * @param id Payment identifier.
     * @return A message about the successful deletion of the payment.
     */
    String deletePaymentById(UUID id);

    /**
     * Creates a new payment.
     *
     * @param paymentCreateDto Data about the payment to be created.
     * @return Data about the created payment.
     */
    PaymentAfterCreationDto createPayment(PaymentCreateDto paymentCreateDto);

    /**
     * Updates payment information by its identifier.
     *
     * @param id               Payment identifier.
     * @param paymentCreateDto Updated payment data.
     * @return Updated payment object.
     */
    Payment updatePaymentById(UUID id, PaymentCreateDto paymentCreateDto);
}
