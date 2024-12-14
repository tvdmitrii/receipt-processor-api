package com.turygin.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    public String retailer;
    public LocalDate purchaseDate;
    public LocalTime purchaseTime;
    public List<Item> items = new ArrayList<>();
    public String total;

    public Receipt(String retailer, LocalDate purchaseDate, LocalTime purchaseTime, String total) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.total = total;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
