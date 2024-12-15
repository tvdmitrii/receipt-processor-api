package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

import java.time.LocalTime;

public class PurchaseTimeWindowRule implements IPointRule {

    private final LocalTime startTime;
    private final LocalTime endTime;
    private final long points;

    public PurchaseTimeWindowRule(LocalTime startTime, LocalTime endTime, long points) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.points = points;
    }

    @Override
    public Long compute(ReceiptEntity receipt) {
        LocalTime purchaseTime = receipt.getPurchaseTime();
        if(purchaseTime.isAfter(startTime) && purchaseTime.isBefore(endTime)) {
            return points;
        }

        return 0L;
    }
}
