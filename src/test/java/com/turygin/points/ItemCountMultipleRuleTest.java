package com.turygin.points;

import com.turygin.storage.ItemEntity;
import com.turygin.storage.ReceiptEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemCountMultipleRuleTest {
    private static final long POINTS_PER_GROUP = 5L;
    private static final ItemCountMultipleRule PAIR = new ItemCountMultipleRule(POINTS_PER_GROUP, 2);
    private static final ItemCountMultipleRule TRIPLE = new ItemCountMultipleRule(POINTS_PER_GROUP, 3);

    @Test
    void emptyItems() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        List<ItemEntity> items = new ArrayList<>();
        receiptEntity.setItems(items);

        assertEquals(0, PAIR.compute(receiptEntity));
        assertEquals(0, TRIPLE.compute(receiptEntity));
    }

    @Test
    void oneItem() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity());
        receiptEntity.setItems(items);

        assertEquals(0, PAIR.compute(receiptEntity));
        assertEquals(0, TRIPLE.compute(receiptEntity));
    }

    @Test
    void twoItems() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity());
        items.add(new ItemEntity());
        receiptEntity.setItems(items);

        assertEquals(POINTS_PER_GROUP, PAIR.compute(receiptEntity));
        assertEquals(0, TRIPLE.compute(receiptEntity));
    }

    @Test
    void threeItems() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        List<ItemEntity> items = new ArrayList<>();
        items.add(new ItemEntity());
        items.add(new ItemEntity());
        items.add(new ItemEntity());
        receiptEntity.setItems(items);

        assertEquals(POINTS_PER_GROUP, PAIR.compute(receiptEntity));
        assertEquals(POINTS_PER_GROUP, TRIPLE.compute(receiptEntity));
    }

    @Test
    void sixItems() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        List<ItemEntity> items = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            items.add(new ItemEntity());
        }
        receiptEntity.setItems(items);

        assertEquals(POINTS_PER_GROUP*3, PAIR.compute(receiptEntity));
        assertEquals(POINTS_PER_GROUP*2, TRIPLE.compute(receiptEntity));
    }
}