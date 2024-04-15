package com.example.carsharing.controller;

import com.example.carsharing.entity.Payment;
import com.example.carsharing.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/get/{id}")
    public Payment getPaymentById(@PathVariable("id") UUID id) {
        return paymentService.getPaymentById(id);
    }
}
