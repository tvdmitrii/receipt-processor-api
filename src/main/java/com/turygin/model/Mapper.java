package com.turygin.model;

import com.turygin.storage.ItemEntity;
import com.turygin.storage.ReceiptEntity;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static ItemEntity toItemEntity(Item item) {
        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setShortDescription(Validator.validateDescription(item.shortDescription));
        itemEntity.setPrice(Validator.validatePrice(item.price));

        return itemEntity;
    }

    public static List<ItemEntity> toItemEntityList(List<Item> items) {
        Validator.throwIfInvalidItemCount(items, 1);
        List<ItemEntity> itemEntities = new ArrayList<>();
        for (Item item : items) {
            itemEntities.add(toItemEntity(item));
        }

        return itemEntities;
    }

    public static ReceiptEntity toReceiptEntity(Receipt receipt) {
        ReceiptEntity receiptEntity = new ReceiptEntity();

        receiptEntity.setRetailer(Validator.validateRetailer(receipt.retailer));
        receiptEntity.setPurchaseDate(Validator.validateDate(receipt.purchaseDate));
        receiptEntity.setPurchaseTime(Validator.validateTime(receipt.purchaseTime));
        receiptEntity.setTotal(Validator.validatePrice(receipt.total));
        receiptEntity.setItems(toItemEntityList(receipt.items));

        return receiptEntity;
    }
}
