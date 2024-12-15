package com.turygin.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validatePrice_ValidPrice() {
        String price = "5.99";
        BigDecimal actual = Validator.validatePrice(price);
        BigDecimal expected = new BigDecimal(price);
        assertEquals(0, actual.compareTo(expected));
    }
    @Test
    void validatePrice_ValidPriceLarge() {
        String price = "1234.56";
        BigDecimal actual = Validator.validatePrice(price);
        BigDecimal expected = new BigDecimal(price);
        assertEquals(0, actual.compareTo(expected));
    }

    @Test
    void validatePrice_LeadingZeros() {
        BigDecimal actual = Validator.validatePrice("0010.50");
        BigDecimal expected = new BigDecimal("10.50");
        assertEquals(0, actual.compareTo(expected));
    }

    @Test
    void validatePrice_RoundDollarAmount() {
        String price = "11.00";
        BigDecimal actual = Validator.validatePrice(price);
        BigDecimal expected = new BigDecimal(price);
        assertEquals(0, actual.compareTo(expected));
    }

    @Test
    void validatePrice_Zero() {
        BigDecimal price = Validator.validatePrice("0.00");
        assertEquals(0, price.compareTo(BigDecimal.ZERO));
    }

    @Test
    void validatePrice_Null() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice(null); });
    }

    @Test
    void validatePrice_Empty() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice(""); });
    }

    @Test
    void validatePrice_Text() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice("hello"); });
    }

    @Test
    void validatePrice_Comma() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice("5,99"); });
    }

    @Test
    void validatePrice_ThreeDecimals() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice("5.999"); });
    }

    @Test
    void validatePrice_OneDecimal() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice("5.9"); });
    }

    @Test
    void validatePrice_NoDecimal() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice("6."); });
    }

    @Test
    void validatePrice_TrailingZeros() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice("5.9900"); });
    }

    @Test
    void validatePrice_LeadingDot() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice(".4900"); });
    }

    @Test
    void validatePrice_MissingDot() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validatePrice("4900"); });
    }

    @Test
    void validateDate_ValidDate() {
        String date = "2024-12-15";
        LocalDate actual = Validator.validateDate(date);
        LocalDate expected = LocalDate.parse(date);
        assertEquals(expected, actual);
    }

    @Test
    void validateDate_Null() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateDate(null); });
    }

    @Test
    void validateDate_Empty() {
        assertThrows(DateTimeParseException.class, () -> { Validator.validateDate(""); });
    }

    @Test
    void validateDate_MissingDay() {
        assertThrows(DateTimeParseException.class, () -> { Validator.validateDate("2024-12"); });
    }

    @Test
    void validateDate_MissingMonthAndDay() {
        assertThrows(DateTimeParseException.class, () -> { Validator.validateDate("2024"); });
    }

    @Test
    void validateDate_Text() {
        assertThrows(DateTimeParseException.class, () -> { Validator.validateDate("hello"); });
    }

    @Test
    void validateDate_WithTime() {
        assertThrows(DateTimeParseException.class, () -> { Validator.validateDate("2024-12-15 12:05"); });
    }

    @Test
    void validateDate_NonISO() {
        assertThrows(DateTimeParseException.class, () -> { Validator.validateDate("2024-15-12"); });
    }

    @Test
    void validateDate_ShortYear() {
        assertThrows(DateTimeParseException.class, () -> { Validator.validateDate("24-12-15"); });
    }

    @Test
    void validateTime_ValidTime() {
        String time = "10:45";
        LocalTime actual = Validator.validateTime(time);
        LocalTime expected = LocalTime.parse(time);
        assertEquals(expected, actual);
    }

    @Test
    void validateTime_ValidTime24Hour() {
        String time = "16:45";
        LocalTime actual = Validator.validateTime(time);
        LocalTime expected = LocalTime.parse(time);
        assertEquals(expected, actual);
    }

    @Test
    void validateTime_Null() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateTime(null); });
    }

    @Test
    void validateTime_Empty() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateTime(""); });
    }

    @Test
    void validateTime_Text() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateTime("hello"); });
    }

    @Test
    void validateTime_NoMinutes() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateTime("12:"); });
    }

    @Test
    void validateTime_Integer() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateTime("12"); });
    }

    @Test
    void validateTime_WithSeconds() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateTime("12:45:14"); });
    }

    @Test
    void validateTime_InvalidHours() {
        assertThrows(DateTimeParseException.class, () -> { Validator.validateTime("25:45"); });
    }

    @Test
    void validateTime_InvalidMinutes() {
        assertThrows(DateTimeParseException.class, () -> { Validator.validateTime("14:61"); });
    }

    @Test
    void validateDescription_ValidDescription() {
        String expected = "Fresh Apple-Orange";
        String actual = Validator.validateDescription(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateDescription_WithNumbers() {
        String expected = "Mountain Dew 12PK";
        String actual = Validator.validateDescription(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateDescription_Dash() {
        String expected = "-";
        String actual = Validator.validateDescription(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateDescription_Whitespace() {
        String expected = "   ";
        String actual = Validator.validateDescription(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateDescription_SingleChar() {
        String expected = "a";
        String actual = Validator.validateDescription(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateDescription_Null() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateDescription(null); });
    }

    @Test
    void validateDescription_Empty() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateDescription(""); });
    }

    @Test
    void validateDescription_IllegalChars() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateDescription("M&/.<Ms"); });
    }

    @Test
    void validateRetailer_ValidRetailer() {
        String expected = "M&M Corner Market";
        String actual = Validator.validateRetailer(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateRetailer_WithNumbers() {
        String expected = "24 Hour Store";
        String actual = Validator.validateRetailer(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateRetailer_Dash() {
        String expected = "-";
        String actual = Validator.validateRetailer(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateRetailer_Ampersand() {
        String expected = "&";
        String actual = Validator.validateRetailer(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateRetailer_Whitespace() {
        String expected = "   ";
        String actual = Validator.validateRetailer(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateRetailer_SingleChar() {
        String expected = "a";
        String actual = Validator.validateRetailer(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validateRetailer_Null() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateRetailer(null); });
    }

    @Test
    void validateRetailer_Empty() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateRetailer(""); });
    }

    @Test
    void validateRetailer_IllegalChars() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateRetailer("24/7 <Store>"); });
    }

    @Test
    void throwIfInvalidItemCount_ItemCountGreater() {
        List<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(2);
        assertDoesNotThrow(() -> {Validator.throwIfInvalidItemCount(items, 1);});
    }

    @Test
    void throwIfInvalidItemCount_ItemCountEqual() {
        List<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(2);
        assertDoesNotThrow(() -> {Validator.throwIfInvalidItemCount(items, 2);});
    }

    @Test
    void throwIfInvalidItemCount_Null() {
        assertThrows(
                IllegalArgumentException.class,
                () -> { Validator.throwIfInvalidItemCount(null, 1); }
        );
    }

    @Test
    void throwIfInvalidItemCount_ItemCountLess() {
        List<Integer> items = new ArrayList<>();
        items.add(1);
        assertThrows(
                IllegalArgumentException.class,
                () -> { Validator.throwIfInvalidItemCount(items, 2); }
        );
    }

    @Test
    void validateUUID_ValidUUID() {
        String uuid = "adb6b560-0eef-42bc-9d16-df48f30e89b2";
        UUID expected = UUID.fromString(uuid);
        UUID actual = Validator.validateUUID(uuid);
        assertEquals(expected, actual);
    }

    @Test
    void validateUUID_Null() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateUUID(null); });
    }

    @Test
    void validateUUID_Empty() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateUUID(""); });
    }

    @Test
    void validateUUID_InvalidUUID() {
        assertThrows(IllegalArgumentException.class, () -> { Validator.validateUUID("adb6b560-0eef-42bc"); });
    }
}