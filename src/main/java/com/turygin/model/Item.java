package com.turygin.model;

/**
 * Receipt purchase item input model.
 */
public class Item {

    /** Short item description. */
    public String shortDescription;

    /** Item price. */
    public String price;

    /** Empty constructor. */
    public Item() {}

    /**
     * Creates a new receipt purchase item model.
     * @param shortDescription item description
     * @param price item price
     */
    public Item(String shortDescription, String price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }
}
