package com.example.carsharing.mapper.util;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Utility class for generating credit card details.
 */
@UtilityClass
public class CardGeneratorUtil {

    /**
     * Generates random credit card details.
     *
     * @return The generated credit card details.
     */
    public static String generateCreditCardDetails() {
        String cardNumber = generateCardNumber();
        String cvv = generateCVV();
        String issueDate = generateIssueDate();

        return cardNumber + " CVV: " + cvv + " ISSUE DATE: " + issueDate;
    }

    /**
     * Generates a random card number following Luhn's algorithm.
     *
     * @return The generated card number.
     */
    private static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
            if ((i + 1) % 4 == 0 && i < 14) {
                cardNumber.append(" ");
            }
        }
        cardNumber.append(generateLuhnDigit(cardNumber.toString().replace(" ", "")));
        return cardNumber.toString();
    }

    /**
     * Generates the last digit of a card number using Luhn's algorithm.
     *
     * @param cardNumber The card number without the last digit.
     * @return The last digit calculated using Luhn's algorithm.
     */
    private static int generateLuhnDigit(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - sum % 10) % 10;
    }

    /**
     * Generates a random CVV code.
     *
     * @return The generated CVV code.
     */
    private static String generateCVV() {
        return String.format("%03d", new Random().nextInt(1000));
    }

    /**
     * Generates a random issue date for the credit card.
     *
     * @return The generated issue date.
     */
    private static String generateIssueDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        Calendar issueDate = Calendar.getInstance();
        return sdf.format(issueDate.getTime());
    }
}
