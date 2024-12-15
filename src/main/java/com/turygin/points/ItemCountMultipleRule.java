package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

public class ItemCountMultipleRule implements IPointRule {

    private final long pointsPerGroup;
    private final long groupSize;

    public ItemCountMultipleRule(long pointsPerGroup, long groupSize) {
        this.pointsPerGroup = pointsPerGroup;
        this.groupSize = groupSize;
    }

    @Override
    public Long compute(ReceiptEntity receipt) {
        return receipt.getItems().size() / groupSize * pointsPerGroup;
    }
}
