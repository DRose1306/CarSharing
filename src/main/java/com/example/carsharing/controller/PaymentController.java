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

@Validated
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    @GetPayment(path = "/get/{id}")
    public Payment getPaymentById(@PathVariable("id") @UuidFormatChecker String id) {
        return paymentService.getPaymentById(UUID.fromString(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeletePayment(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePaymentById(@PathVariable("id") @UuidFormatChecker String id) {
        return paymentService.deletePaymentById(UUID.fromString(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @CreatePayment(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentAfterCreationDto createPayment(@RequestBody PaymentCreateDto paymentCreateDto) {
        return paymentService.createPayment(paymentCreateDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @UpdatePayment(path = "/update/{id}")
    public Payment updatePayment(@PathVariable("id") @UuidFormatChecker String id, @RequestBody PaymentCreateDto paymentCreateDto) {
        return paymentService.updatePaymentById(UUID.fromString(id), paymentCreateDto);
    }
}
