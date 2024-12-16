package com.turygin.points;

import com.turygin.storage.ItemEntity;
import com.turygin.storage.ReceiptEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointPipelineTest {

    private static final PointPipeline POINT_PIPELINE = new PointPipeline();

    @BeforeAll
    static void setUp() {
        // One point for every alphanumeric character in the retailer name.
        POINT_PIPELINE.addRule(new RetailerCharCountRule("[a-zA-Z0-9]", 1L));
        // 50 points if the total is a round dollar amount with no cents.
        POINT_PIPELINE.addRule(new TotalMultipleRule(50L, BigDecimal.ONE));
        // 25 points if the total is a multiple of 0.25.
        POINT_PIPELINE.addRule(new TotalMultipleRule(25L, new BigDecimal("0.25")));
        // 5 points for every two items on the receipt.
        POINT_PIPELINE.addRule(new ItemCountMultipleRule(5L, 2L));
        // If the trimmed length of the item description is a multiple of 3,
        // multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        POINT_PIPELINE.addRule(new ItemDescriptionLengthRule(3L, new BigDecimal("0.2")));
        // 6 points if the day in the purchase date is odd.
        POINT_PIPELINE.addRule(new OddPurchaseDateRule(6L));
        // 10 points if the time of purchase is after 2:00pm and before 4:00pm.
        POINT_PIPELINE.addRule(
                new PurchaseTimeWindowRule(LocalTime.of(14, 0), LocalTime.of(16, 0), 10L)
        );
    }

    @Test
    void testExample1() {
        ReceiptEntity receiptEntity = new ReceiptEntity(
                "Target",
                LocalDate.parse("2022-01-01"),
                LocalTime.parse("13:01"),
                new BigDecimal("35.35")
        );

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("Mountain Dew 12PK", new BigDecimal("6.49")));
        items.add(new ItemEntity("Emils Cheese Pizza", new BigDecimal("12.25")));
        items.add(new ItemEntity("Knorr Creamy Chicken", new BigDecimal("1.26")));
        items.add(new ItemEntity("Doritos Nacho Cheese", new BigDecimal("3.35")));
        items.add(new ItemEntity("   Klarbrunn 12-PK 12 FL OZ  ", new BigDecimal("12.00")));
        receiptEntity.setItems(items);

        assertEquals(28L, POINT_PIPELINE.compute(receiptEntity));
    }

    @Test
    void testExample2() {
        ReceiptEntity receiptEntity = new ReceiptEntity(
                "M&M Corner Market",
                LocalDate.parse("2022-03-20"),
                LocalTime.parse("14:33"),
                new BigDecimal("9.00")
        );

        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity("Gatorade", new BigDecimal("2.25")));
        items.add(new ItemEntity("Gatorade", new BigDecimal("2.25")));
        items.add(new ItemEntity("Gatorade", new BigDecimal("2.25")));
        items.add(new ItemEntity("Gatorade", new BigDecimal("2.25")));
        receiptEntity.setItems(items);

        assertEquals(109L, POINT_PIPELINE.compute(receiptEntity));
    }
}