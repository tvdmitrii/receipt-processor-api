package com.turygin.storage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Receipt entity class used for storage and back-end receipt processing.
 */
public class ReceiptEntity {

    /** Retailer name. */
    private String retailer;

    /** Purchase date (ISO format). */
    private LocalDate purchaseDate;

    /** Purchase time (24-hour format without seconds). */
    private LocalTime purchaseTime;

    /** A list of purchased items. */
    private List<ItemEntity> items = new ArrayList<>();

    /** Total receipt amount. */
    private BigDecimal total;

    /** Empty constructor. */
    public ReceiptEntity() {}

    /**
     * Creates a new receipt entity.
     * @param retailer retailer name
     * @param purchaseDate purchase date (ISO format)
     * @param purchaseTime purchase time (24-hour format without seconds)
     * @param total total receipt amount
     */
    public ReceiptEntity(String retailer, LocalDate purchaseDate, LocalTime purchaseTime, BigDecimal total) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.total = total;
    }

    /**
     * Gets receipt items.
     * @return list of purchased items
     */
    public List<ItemEntity> getItems() {
        return items;
    }

    /**
     * Sets receipt items.
     * @param items list of purchased items
     */
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    /**
     * Gets purchase date.
     * @return purchase date
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets purchase date.
     * @param purchaseDate purchase date
     */
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Gets purchase time.
     * @return the purchase time
     */
    public LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    /**
     * Sets purchase time.
     * @param purchaseTime the purchase time
     */
    public void setPurchaseTime(LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    /**
     * Gets retailer name.
     * @return the retailer name
     */
    public String getRetailer() {
        return retailer;
    }

    /**
     * Sets retailer name.
     * @param retailer the retailer name
     */
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    /**
     * Gets receipt total.
     * @return the receipt total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Sets receipt total.
     * @param total the receipt total
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
