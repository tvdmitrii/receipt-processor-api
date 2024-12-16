package com.turygin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Receipt input model.
 */
public class Receipt {

    /** Retailer name. */
    public String retailer;

    /** Purchase date. */
    public String purchaseDate;

    /** Purchase time. */
    public String purchaseTime;

    /** Receipt purchase item list. */
    public List<Item> items = new ArrayList<>();

    /** Total receipt amount. */
    public String total;

    /** Empty constructor. */
    public Receipt() {}

    /**
     * Creates new receipt model.
     * @param retailer retailer name
     * @param purchaseDate purchase date
     * @param purchaseTime purchase time
     * @param total receipt total
     */
    public Receipt(String retailer, String purchaseDate, String purchaseTime, String total) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.total = total;
    }
}
