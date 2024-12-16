package com.turygin.points;

import com.turygin.storage.ItemEntity;
import com.turygin.storage.ReceiptEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Awards points as a multiple of receipt item price if trimmed item description length is a multiple of a value.
 */
public class ItemDescriptionLengthRule implements IPointRule {

    /** The number the trimmed description length must be a multiple of. */
    private final long lengthMultiple;

    /** Price to points multiplier for items that match the requirement. */
    private final BigDecimal priceMultiplier;

    /**
     * Creates a new item description length multiple rule with parameters provided.
     * @param lengthMultiple the value that the trimmed description length must be a multiple of
     * @param priceMultiplier price to points multiplier for matching items
     */
    public ItemDescriptionLengthRule(long lengthMultiple, BigDecimal priceMultiplier) {
        this.lengthMultiple = lengthMultiple;
        this.priceMultiplier = priceMultiplier;
    }

    /**
     * Awards points if the rule applies.
     * @param receipt receipt entity
     * @return the number of points awarded
     */
    @Override
    public Long compute(ReceiptEntity receipt) {
        long points = 0L;
        for(ItemEntity item : receipt.getItems()) {
            String descriptionTrimmed = item.getShortDescription().trim();

            if(descriptionTrimmed.length() % lengthMultiple == 0) {
                // Round up to the nearest integer point value
                points += item.getPrice().multiply(priceMultiplier).
                        setScale(0, RoundingMode.CEILING).longValue();
            }
        }

        return points;
    }
}
