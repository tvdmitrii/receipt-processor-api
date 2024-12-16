package com.turygin.points;


import com.turygin.storage.ReceiptEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
class TotalMultipleRuleTest {

    private static final Long POINTS = 10L;
    private static final TotalMultipleRule ROUND = new TotalMultipleRule(POINTS, BigDecimal.ONE);
    private static final TotalMultipleRule QUARTER = new TotalMultipleRule(POINTS, new BigDecimal("0.25"));
    private static final TotalMultipleRule THIRD = new TotalMultipleRule(POINTS, new BigDecimal("0.33"));

    @Test
    void roundAmount_Valid() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        receiptEntity.setTotal(BigDecimal.ZERO);
        assertEquals(POINTS, ROUND.compute(receiptEntity));

        receiptEntity.setTotal(BigDecimal.ONE);
        assertEquals(POINTS, ROUND.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("6.00"));
        assertEquals(POINTS, ROUND.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("10.00"));
        assertEquals(POINTS, ROUND.compute(receiptEntity));
    }

    @Test
    void roundAmount_Invalid() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        receiptEntity.setTotal(new BigDecimal("10.01"));
        assertEquals(0, ROUND.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("5.99"));
        assertEquals(0, ROUND.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("4.50"));
        assertEquals(0, ROUND.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("3.63"));
        assertEquals(0, ROUND.compute(receiptEntity));
    }

    @Test
    void quarterMultiple_Valid() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        receiptEntity.setTotal(BigDecimal.ZERO);
        assertEquals(POINTS, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(BigDecimal.ONE);
        assertEquals(POINTS, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("0.25"));
        assertEquals(POINTS, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("6.00"));
        assertEquals(POINTS, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("10.25"));
        assertEquals(POINTS, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("17.50"));
        assertEquals(POINTS, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("130.75"));
        assertEquals(POINTS, QUARTER.compute(receiptEntity));
    }

    @Test
    void quarterMultiple_Invalid() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        receiptEntity.setTotal(new BigDecimal("0.20"));
        assertEquals(0, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("6.12"));
        assertEquals(0, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("10.26"));
        assertEquals(0, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("17.72"));
        assertEquals(0, QUARTER.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("130.99"));
        assertEquals(0, QUARTER.compute(receiptEntity));
    }

    @Test
    void thirdMultiple_Valid() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        receiptEntity.setTotal(BigDecimal.ZERO);
        assertEquals(POINTS, THIRD.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("0.33"));
        assertEquals(POINTS, THIRD.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("0.66"));
        assertEquals(POINTS, THIRD.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("0.99"));
        assertEquals(POINTS, THIRD.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("8.25"));
        assertEquals(POINTS, THIRD.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("33.00"));
        assertEquals(POINTS, THIRD.compute(receiptEntity));
    }

    @Test
    void thirdMultiple_Invalid() {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        receiptEntity.setTotal(new BigDecimal("0.20"));
        assertEquals(0, THIRD.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("0.34"));
        assertEquals(0, THIRD.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("3.99"));
        assertEquals(0, THIRD.compute(receiptEntity));

        receiptEntity.setTotal(BigDecimal.ONE);
        assertEquals(0, THIRD.compute(receiptEntity));

        receiptEntity.setTotal(new BigDecimal("50.00"));
        assertEquals(0, THIRD.compute(receiptEntity));
    }
}