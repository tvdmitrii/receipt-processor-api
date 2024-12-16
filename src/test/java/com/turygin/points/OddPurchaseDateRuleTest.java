package com.turygin.points;

import com.turygin.storage.ReceiptEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OddPurchaseDateRuleTest {

    private static final long POINTS = 6L;
    private static final OddPurchaseDateRule RULE = new OddPurchaseDateRule(POINTS);

    @Test
    void oddDate(){
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setPurchaseDate(LocalDate.parse("2024-12-13"));

        assertEquals(POINTS, RULE.compute(receiptEntity));
    }

    @Test
    void evenDate(){
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setPurchaseDate(LocalDate.parse("2024-12-02"));

        assertEquals(0, RULE.compute(receiptEntity));
    }
}