package com.example.carsharing.controller;

import com.example.carsharing.annotation.*;
import com.example.carsharing.dto.PaymentAfterCreationDto;
import com.example.carsharing.dto.PaymentCreateDto;
import com.example.carsharing.entity.Payment;
import com.example.carsharing.service.interfaces.PaymentService;
import com.example.carsharing.validation.annotation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller class responsible for managing payment-related HTTP requests.
 */
@Validated
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    /**
     * Retrieves payment information by its ID.
     *
     * @param id The ID of the payment to retrieve.
     * @return The payment object.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    @GetPayment(path = "/get/{id}")
    public Payment getPaymentById(@PathVariable("id") @UuidFormatChecker String id) {
        return paymentService.getPaymentById(UUID.fromString(id));
    }

    /**
     * Deletes a payment by its ID.
     *
     * @param id The ID of the payment to delete.
     * @return A message indicating the success of the operation.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeletePayment(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePaymentById(@PathVariable("id") @UuidFormatChecker String id) {
        return paymentService.deletePaymentById(UUID.fromString(id));
    }

    /**
     * Creates a new payment.
     *
     * @param paymentCreateDto The DTO containing the information for creating the new payment.
     * @return The DTO containing information about the newly created payment.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @CreatePayment(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentAfterCreationDto createPayment(@RequestBody PaymentCreateDto paymentCreateDto) {
        return paymentService.createPayment(paymentCreateDto);
    }

    /**
     * Updates payment information.
     *
     * @param id               The ID of the payment to update.
     * @param paymentCreateDto The DTO containing the updated payment information.
     * @return The updated payment object.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @UpdatePayment(path = "/update/{id}")
    public Payment updatePayment(@PathVariable("id") @UuidFormatChecker String id, @RequestBody PaymentCreateDto paymentCreateDto) {
        return paymentService.updatePaymentById(UUID.fromString(id), paymentCreateDto);
    }
}
