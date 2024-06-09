package com.example.carsharing.dto;

import lombok.Data;

/**
 * DTO representing payment details after creation.
 */
@Data
public class PaymentAfterCreationDto {

    /**
     * The amount of the payment.
     */
    private double amount;

    /**
     * The date of the payment.
     */
    private String paymentDate;

    /**
     * The payment method used.
     */
    private String paymentMethod;

    /**
     * The user associated with the payment.
     */
    private String user;

    /**
     * The status of the payment.
     */
    private boolean status;
}
