package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

public class OddPurchaseDateRule implements IPointRule {

    private final long points;

    public OddPurchaseDateRule(long points) {
        this.points = points;
    }

    @Override
    public Long compute(ReceiptEntity receipt) {
        return receipt.getPurchaseDate().getDayOfMonth() % 2 == 1 ? points : 0L;
    }
}
