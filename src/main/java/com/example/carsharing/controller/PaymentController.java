package com.example.carsharing.controller;

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

    @GetMapping("/get_payment/{id}")
    public Payment getPaymentById(@PathVariable("id") UUID id) {
        return paymentService.getPaymentById(id);
    }

    //TODO никак не хочет работать
    @DeleteMapping("/delete_payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePaymentById(@PathVariable("id") UUID id) {
        paymentService.deletePaymentById(id);
    }

    @PostMapping("/create_payment")
    public PaymentAfterCreationDto createPayment(@RequestBody PaymentCreateDto paymentCreateDto){
        return paymentService.createPayment(paymentCreateDto);
    }

    @PutMapping("/update_payment/{id}")
    public Payment updatePayment(@PathVariable("id") UUID id, @RequestBody PaymentCreateDto paymentCreateDto){
        return paymentService.updatePaymentById(id, paymentCreateDto);
    }
}
