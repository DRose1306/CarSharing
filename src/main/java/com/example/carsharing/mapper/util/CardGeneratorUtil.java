package com.example.carsharing.mapper.util;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

@UtilityClass
public class CardGeneratorUtil {
    public static String generateCreditCardDetails() {
        String cardNumber = generateCardNumber();
        String cvv = generateCVV();
        String issueDate = generateIssueDate();

        return cardNumber + " CVV: " + cvv + " ISSUE DATE: " + issueDate;
    }

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

    private static String generateCVV() {
        return String.format("%03d", new Random().nextInt(1000));
    }

    private static String generateIssueDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        Calendar issueDate = Calendar.getInstance();
        return sdf.format(issueDate.getTime());
    }
}
