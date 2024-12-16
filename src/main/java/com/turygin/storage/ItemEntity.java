package com.turygin.storage;

import java.math.BigDecimal;

/**
 * Receipt purchase item entity class used for storage and back-end receipt processing.
 */
public class ItemEntity {

    /** Item description. */
    private String shortDescription;

    /** Item price. */
    private BigDecimal price;

    /** Empty constructor. */
    public ItemEntity() {}

    /**
     * Creates a new receipt entity.
     * @param shortDescription item description
     * @param price item price
     */
    public ItemEntity(String shortDescription, BigDecimal price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    /**
     * Gets item price.
     * @return the item price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets item price.
     * @param price the item price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets item description.
     * @return the item description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets item description.
     * @param shortDescription the item description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
