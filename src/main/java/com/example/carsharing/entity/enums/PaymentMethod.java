package com.example.carsharing.entity.enums;

import lombok.*;

/**
 * Enum representing different payment methods.
 * Each method corresponds to a specific way of making payments.
 */
@Getter
@AllArgsConstructor
public enum PaymentMethod {
    VISA,
    MASTERCARD,
    AMERICAN_EXPRESS,
    SEPA_DIRECT_DEBIT,
    PAYPAL,
    APPLE_PAY,
    GOOGLE_PAY
}
