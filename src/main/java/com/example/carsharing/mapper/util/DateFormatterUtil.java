package com.example.carsharing.mapper.util;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Utility class for formatting dates.
 */
@UtilityClass
public class DateFormatterUtil {

    /**
     * Formats the given timestamp based on the country's format.
     *
     * @param timestamp The timestamp to format.
     * @param country   The country code indicating the desired format.
     * @return The formatted date string.
     */
    public static String formatDataByCountry(Timestamp timestamp, String country) {
        SimpleDateFormat formatted = switch (country) {
            case "USA" -> new SimpleDateFormat("MM/dd/yyyy ");
            case "Germany" -> new SimpleDateFormat("dd-MM-yyyy");
            default -> new SimpleDateFormat("dd.MM.yyyy");
        };
        return formatted.format(timestamp);
    }

    /**
     * Formats the given timestamp to the default format.
     *
     * @param timestamp The timestamp to format.
     * @return The formatted date string.
     */
    public static String formatData(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(timestamp);
    }
}
