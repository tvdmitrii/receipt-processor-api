package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

/**
 * Awards points if a purchase was made on an odd day of the month.
 */
public class OddPurchaseDateRule implements IPointRule {

    /** The number of points to award. */
    private final long points;

    /**
     * Creates a new idd purchase date rule with parameters provided.
     * @param points the number of points to award if purchased on an odd day of the month
     */
    public OddPurchaseDateRule(long points) {
        this.points = points;
    }

    /**
     * Awards points if the rule applies.
     * @param receipt receipt entity
     * @return the number of points awarded
     */
    @Override
    public Long compute(ReceiptEntity receipt) {
        return receipt.getPurchaseDate().getDayOfMonth() % 2 == 1 ? points : 0L;
    }
}
