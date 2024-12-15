package com.turygin.points;

import com.turygin.storage.ItemEntity;
import com.turygin.storage.ReceiptEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ItemDescriptionLengthRule implements IPointRule {

    private final int lengthMultiple;
    private final BigDecimal priceMultiplier;

    public ItemDescriptionLengthRule(int lengthMultiple, String priceMultiplier) {
        this.lengthMultiple = lengthMultiple;
        this.priceMultiplier = new BigDecimal(priceMultiplier);
    }

    @Override
    public Long compute(ReceiptEntity receipt) {
        long points = 0L;
        for(ItemEntity item : receipt.getItems()) {
            String descriptionTrimmed = item.getShortDescription().trim();
            if(descriptionTrimmed.length() % lengthMultiple == 0) {
                points += item.getPrice().multiply(priceMultiplier).
                        setScale(0, RoundingMode.HALF_UP).longValue();
            }
        }
        return points;
    }
}
