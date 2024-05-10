package com.example.carsharing.controller;

import com.example.carsharing.annotation.CreatePayment;
import com.example.carsharing.annotation.DeleteTrip;
import com.example.carsharing.annotation.GetPayment;
import com.example.carsharing.annotation.UpdateTrip;
import com.example.carsharing.dto.PaymentAfterCreationDto;
import com.example.carsharing.dto.PaymentCreateDto;
import com.example.carsharing.entity.Payment;
import com.example.carsharing.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetPayment(path = "/get/{id}")
    public Payment getPaymentById(@PathVariable("id") UUID id) {
        return paymentService.getPaymentById(id);
    }

    @DeleteTrip(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePaymentById(@PathVariable("id") UUID id) {
        return paymentService.deletePaymentById(id);
    }

    @CreatePayment(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentAfterCreationDto createPayment(@RequestBody PaymentCreateDto paymentCreateDto){
        return paymentService.createPayment(paymentCreateDto);
    }

    @UpdateTrip(path = "/update/{id}")
    public Payment updatePayment(@PathVariable("id") UUID id, @RequestBody PaymentCreateDto paymentCreateDto){
        return paymentService.updatePaymentById(id, paymentCreateDto);
    }
}
