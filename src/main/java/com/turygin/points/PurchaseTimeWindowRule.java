package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

import java.time.LocalTime;

/**
 * Awards points if a purchase was made within a specified time window.
 * Note that time window bounds are exclusive.
 */
public class PurchaseTimeWindowRule implements IPointRule {

    /** Window start time. */
    private final LocalTime startTime;

    /** Window end time. */
    private final LocalTime endTime;

    /** Number of points to award if a purchase falls within the time window. */
    private final long points;

    /**
     * Creates a new purchase time window rule with parameters provided.
     * @param startTime window start time (exclusive)
     * @param endTime window end time (exclusive)
     * @param points the number of points to award if a purchase is inside of the window
     */
    public PurchaseTimeWindowRule(LocalTime startTime, LocalTime endTime, long points) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.points = points;
    }

    /**
     * Awards points if the rule applies.
     * @param receipt receipt entity
     * @return the number of points awarded
     */
    @Override
    public Long compute(ReceiptEntity receipt) {
        LocalTime purchaseTime = receipt.getPurchaseTime();

        if(purchaseTime.isAfter(startTime) && purchaseTime.isBefore(endTime)) {
            return points;
        }

        return 0L;
    }
}
