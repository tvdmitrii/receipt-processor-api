package com.turygin.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    public String retailer;
    public String purchaseDate;
    public String purchaseTime;
    public List<Item> items = new ArrayList<>();
    public String total;

    public Receipt(String retailer, String purchaseDate, String purchaseTime, String total) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.total = total;
    }
}
