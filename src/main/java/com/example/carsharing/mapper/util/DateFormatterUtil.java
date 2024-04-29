package com.example.carsharing.mapper.util;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@UtilityClass
public class DateFormatterUtil {
    public static String formatDataByCountry(Timestamp timestamp, String country) {
        SimpleDateFormat formatted = switch (country) {
            case "USA" -> new SimpleDateFormat("MM/dd/yyyy ");
            case "Germany" -> new SimpleDateFormat("dd-MM-yyyy");
            default -> new SimpleDateFormat("dd.MM.yyyy");
        };
        return formatted.format(timestamp);
    }

    public static String formatData(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(timestamp);
    }
}
