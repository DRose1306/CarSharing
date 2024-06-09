package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.PaymentMethod;
import lombok.Data;

/**
 * DTO representing payment creation details.
 */
@Data
public class PaymentCreateDto {

    /**
     * The amount of the payment.
     */
    double amount;

    /**
     * The date of the payment.
     */
    String paymentDate;

    /**
     * The payment method used.
     */
    PaymentMethod paymentMethod;

    /**
     * The ID of the user associated with the payment.
     */
    String userId;
}
