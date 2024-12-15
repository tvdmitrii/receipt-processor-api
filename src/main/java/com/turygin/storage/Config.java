package com.turygin.storage;

import com.turygin.points.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Config {

    private static final Logger LOG = LogManager.getLogger(Config.class);
    private static Config instance = null;

    private final ReceiptManager receiptManager;
    private final PointPipeline pointPipeline;

    private Config() {
        receiptManager = new ReceiptManager();

        pointPipeline = new PointPipeline();
        // One point for every alphanumeric character in the retailer name.
        pointPipeline.addRule(new RetailerCharCountRule("[a-zA-Z0-9]", 1L));
        // 50 points if the total is a round dollar amount with no cents.
        pointPipeline.addRule(new TotalMultipleRule(50L, BigDecimal.ONE));
        // 25 points if the total is a multiple of 0.25.
        pointPipeline.addRule(new TotalMultipleRule(25L, new BigDecimal("0.25")));
        // 5 points for every two items on the receipt.
        pointPipeline.addRule(new ItemCountMultipleRule(5L, 2L));
        // If the trimmed length of the item description is a multiple of 3,
        // multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        pointPipeline.addRule(new ItemDescriptionLengthRule(3L, new BigDecimal("0.2")));
        // 6 points if the day in the purchase date is odd.
        pointPipeline.addRule(new OddPurchaseDateRule(6L));
        // 10 points if the time of purchase is after 2:00pm and before 4:00pm.
        pointPipeline.addRule(
                new PurchaseTimeWindowRule(LocalTime.of(14, 0), LocalTime.of(16, 0), 10L)
        );
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }

    public ReceiptManager getReceiptManager() {
        return receiptManager;
    }

    public PointPipeline getPointPipeline() {
        return pointPipeline;
    }
}
