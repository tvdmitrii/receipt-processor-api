package com.turygin.points;

import com.turygin.storage.ItemEntity;
import com.turygin.storage.ReceiptEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDescriptionLengthRuleTest {
    private static final ItemDescriptionLengthRule MULTIPLE_OF_THREE =
            new ItemDescriptionLengthRule(3L, new BigDecimal("0.2"));

    private static final ItemDescriptionLengthRule MULTIPLE_OF_FOUR =
            new ItemDescriptionLengthRule(4L, new BigDecimal("0.25"));

    @Test
    void validLength() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("abcdef", new BigDecimal("12.99")));
        receiptEntity.setItems(items);

        assertEquals(3L, MULTIPLE_OF_THREE.compute(receiptEntity));
        assertEquals(0L, MULTIPLE_OF_FOUR.compute(receiptEntity));
    }

    @Test
    void validForBothLength() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("abcdefabcdef", new BigDecimal("12.99")));
        receiptEntity.setItems(items);

        assertEquals(3L, MULTIPLE_OF_THREE.compute(receiptEntity));
        assertEquals(4L, MULTIPLE_OF_FOUR.compute(receiptEntity));
    }

    @Test
    void halfPoint() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("abcdef", new BigDecimal("12.50")));
        receiptEntity.setItems(items);

        assertEquals(3L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }

    @Test
    void lessThanHalfPoint() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("abcdef", new BigDecimal("12.00")));
        receiptEntity.setItems(items);

        assertEquals(3L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }

    @Test
    void greaterThanHalfPoint() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("abcdef", new BigDecimal("14.45")));
        receiptEntity.setItems(items);

        assertEquals(3L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }

    @Test
    void integerPoints() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("abcdef", new BigDecimal("100.00")));
        receiptEntity.setItems(items);

        assertEquals(20L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }

    @Test
    void withSpaces() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("abc ef", new BigDecimal("12.50")));
        receiptEntity.setItems(items);

        assertEquals(3L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }

    @Test
    void ValidWithSpacesAround() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("  abc ef  ", new BigDecimal("12.50")));
        receiptEntity.setItems(items);

        assertEquals(3L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }

    @Test
    void LeadingAndTrailingSpaces() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity(" a ", new BigDecimal("12.50")));
        receiptEntity.setItems(items);

        assertEquals(0L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }

    @Test
    void LeadingSpaces() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("  a", new BigDecimal("12.50")));
        receiptEntity.setItems(items);

        assertEquals(0L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }

    @Test
    void TrailingSpaces() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("  a", new BigDecimal("12.50")));
        receiptEntity.setItems(items);

        assertEquals(0L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }

    @Test
    void MultipleItems() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("  a", new BigDecimal("12.50")));
        items.add(new ItemEntity("abc", new BigDecimal("11.00")));
        items.add(new ItemEntity("abcdef", new BigDecimal("14.45")));
        items.add(new ItemEntity("abc  f", new BigDecimal("100.00")));
        items.add(new ItemEntity("abc  f ", new BigDecimal("23.45")));
        receiptEntity.setItems(items);

        assertEquals(31L, MULTIPLE_OF_THREE.compute(receiptEntity));
    }
}