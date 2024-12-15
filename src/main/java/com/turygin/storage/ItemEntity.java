package com.turygin.storage;

import java.math.BigDecimal;

public class ItemEntity {
    private String shortDescription;
    private BigDecimal price;

    public ItemEntity() {}

    public ItemEntity(String shortDescription, BigDecimal price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
