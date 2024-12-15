package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

public class ItemCountMultipleRule implements IPointRule {

    private final long pointsPerPair;
    private final int count;

    public ItemCountMultipleRule(long pointsPerPair, int count) {
        this.pointsPerPair = pointsPerPair;
        this.count = count;
    }

    @Override
    public Long compute(ReceiptEntity receipt) {
        return receipt.getItems().size() / count * pointsPerPair;
    }
}
