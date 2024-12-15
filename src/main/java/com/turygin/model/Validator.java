package com.turygin.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static void throwIfNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object is null.");
        }
    }
    public static BigDecimal validatePrice(String price) {
        throwIfNull(price);
        if (!price.matches("^\\d+\\.\\d{2}$")) {
            throw new IllegalArgumentException("Invalid price.");
        }

        return new BigDecimal(price);
    }

    public static LocalDate validateDate(String date) {
        throwIfNull(date);
        return LocalDate.parse(date);
    }

    public static LocalTime validateTime(String time) {
        throwIfNull(time);
        if (!time.matches("^\\d{2}:\\d{2}$")) {
            throw new IllegalArgumentException("Invalid time.");
        }

        return LocalTime.parse(time);
    }

    public static String validateDescription(String description) {
        throwIfNull(description);
        if (!description.matches("^[\\w\\s\\-]+$")) {
            throw new IllegalArgumentException("Invalid description.");
        }

        return description;
    }

    public static String validateRetailer(String retailer) {
        throwIfNull(retailer);
        if (!retailer.matches("^[\\w\\s\\-&]+$")) {
            throw new IllegalArgumentException("Invalid description.");
        }

        return retailer;
    }

    public static void throwIfInvalidItemCount(List<?> items, int minCount) {
        throwIfNull(items);
        if(items.size() < minCount) {
            throw new IllegalArgumentException("Invalid item count.");
        }
    }

    public static UUID validateUUID(String uuid) {
        throwIfNull(uuid);
        return UUID.fromString(uuid);
    }
}
