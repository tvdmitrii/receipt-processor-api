package com.turygin.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

/**
 * Validator class that validates and parses input model properties.
 */
public class Validator {

    /**
     * Helper method that throws an exception if provided argument is null.
     * @param object object to check for null
     * @throws IllegalArgumentException if object is null
     */
    private static void throwIfNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object is null.");
        }
    }

    /**
     * Validates and parses price, e.g "5.49".
     * @param price price string
     * @return validated and parsed price
     * @throws IllegalArgumentException if input is null or does not conform to the pattern
     */
    public static BigDecimal validatePrice(String price) {
        throwIfNull(price);
        if (!price.matches("^\\d+\\.\\d{2}$")) {
            throw new IllegalArgumentException("Invalid price.");
        }

        return new BigDecimal(price);
    }

    /**
     * Validates and parses date. Expects ISO date standard YYYY-MM-DD.
     * @param date date string
     * @return validated and parsed date
     * @throws IllegalArgumentException if input is null or does not conform to the pattern
     */
    public static LocalDate validateDate(String date) {
        throwIfNull(date);
        return LocalDate.parse(date);
    }

    /**
     * Validates and parses local time. Expects hours and minutes only in 24-hour format, e.g. "12:15".
     * @param time local time string
     * @return validated and parsed time
     * @throws IllegalArgumentException if input is null or does not conform to the pattern
     */
    public static LocalTime validateTime(String time) {
        throwIfNull(time);
        if (!time.matches("^\\d{2}:\\d{2}$")) {
            throw new IllegalArgumentException("Invalid time.");
        }

        return LocalTime.parse(time);
    }

    /**
     * Validates descriptions. Expects at least one of: word characters, whitespace characters, or "-".
     * @param description description string
     * @return unchanged description string argument
     * @throws IllegalArgumentException if input is null or does not conform to the pattern
     */
    public static String validateDescription(String description) {
        throwIfNull(description);
        if (!description.matches("^[\\w\\s\\-]+$")) {
            throw new IllegalArgumentException("Invalid description.");
        }

        return description;
    }

    /**
     * Validates retailer name. Expects at least one of: word characters, whitespace characters, "-", or "&".
     * @param retailer retailer name string
     * @return unchanged retailer name string argument
     * @throws IllegalArgumentException if input is null or does not conform to the pattern
     */
    public static String validateRetailer(String retailer) {
        throwIfNull(retailer);
        if (!retailer.matches("^[\\w\\s\\-&]+$")) {
            throw new IllegalArgumentException("Invalid description.");
        }

        return retailer;
    }

    /**
     * Validates that the number of items in the list is greater or equal to the minimal count.
     * @param items a list of items
     * @param minCount minimal allowed count of items
     * @throws IllegalArgumentException if list is null or has fewer than minimum number of items
     */
    public static void throwIfInvalidItemCount(List<?> items, int minCount) {
        throwIfNull(items);
        if(items.size() < minCount) {
            throw new IllegalArgumentException("Invalid item count.");
        }
    }

    /**
     * Validates and parses UUID.
     * @param uuid UUID string
     * @return validated and parsed UUID
     * @throws IllegalArgumentException if input is null or is not a valid UUID
     */
    public static UUID validateUUID(String uuid) {
        throwIfNull(uuid);
        return UUID.fromString(uuid);
    }
}
