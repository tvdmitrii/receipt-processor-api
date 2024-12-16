package com.turygin.model;

import com.turygin.storage.ItemEntity;
import com.turygin.storage.ReceiptEntity;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class handles model validation and conversion from models to entities.
 */
public class Mapper {

    /**
     * Validate item model and convert it to an item entity.
     * @param item item input model
     * @return validated item entity
     * @throws IllegalArgumentException if item model does not pass validation
     */
    public static ItemEntity toItemEntity(Item item) {
        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setShortDescription(Validator.validateDescription(item.shortDescription));
        itemEntity.setPrice(Validator.validatePrice(item.price));

        return itemEntity;
    }

    /**
     * Validates item model list and coverts it to an item entity list.
     * @param items item input model list
     * @return validated item entity list
     * @throws IllegalArgumentException if at least one item model does not pass validation
     */
    public static List<ItemEntity> toItemEntityList(List<Item> items) {
        Validator.throwIfInvalidItemCount(items, 1);
        List<ItemEntity> itemEntities = new ArrayList<>();
        for (Item item : items) {
            itemEntities.add(toItemEntity(item));
        }

        return itemEntities;
    }

    /**
     * Validate receipt model and convert it to a receipt entity.
     * @param receipt receipt input model
     * @return validated receipt entity
     * @throws IllegalArgumentException if at least receipt does not pass validation
     * @throws DateTimeParseException if purchase date/time does not pass validation
     */
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
