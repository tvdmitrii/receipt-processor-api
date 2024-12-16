package com.turygin.points;

import com.turygin.storage.ReceiptEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetailerCharCountRuleTest {

    private static final RetailerCharCountRule RULE = new RetailerCharCountRule("[a-zA-Z0-9]", 1L);

    @Test
    void noAlphanumCharacters() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setRetailer("- &");
        assertEquals(0, RULE.compute(receiptEntity));
    }

    @Test
    void pureWhitespace() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setRetailer("   ");
        assertEquals(0, RULE.compute(receiptEntity));
    }

    @Test
    void lettersAndNumbers() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setRetailer("Abc123");
        assertEquals(6, RULE.compute(receiptEntity));
    }

    @Test
    void withWhitespace() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setRetailer("Target Superstore-2");
        assertEquals(17, RULE.compute(receiptEntity));
    }

}