package com.turygin.points;

import com.turygin.storage.ReceiptEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTimeWindowRuleTest {
    private static final long POINTS = 10L;
    private static final LocalTime START_TIME = LocalTime.of(14, 0);
    private static final LocalTime END_TIME = LocalTime.of(16, 0);
    private static final PurchaseTimeWindowRule RULE = new PurchaseTimeWindowRule(START_TIME, END_TIME, POINTS);

    @Test
    void timeInsideWindow() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setPurchaseTime(LocalTime.parse("15:13"));

        assertEquals(POINTS, RULE.compute(receiptEntity));
    }

    @Test
    void timeAfterWindow() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setPurchaseTime(LocalTime.parse("17:00"));

        assertEquals(0, RULE.compute(receiptEntity));
    }

    @Test
    void timeBeforeWindow() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setPurchaseTime(LocalTime.parse("07:43"));

        assertEquals(0, RULE.compute(receiptEntity));
    }

    @Test
    void startTimeExcluded() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setPurchaseTime(START_TIME);

        assertEquals(0, RULE.compute(receiptEntity));
    }

    @Test
    void endTimeExcluded() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setPurchaseTime(END_TIME);

        assertEquals(0, RULE.compute(receiptEntity));
    }
}