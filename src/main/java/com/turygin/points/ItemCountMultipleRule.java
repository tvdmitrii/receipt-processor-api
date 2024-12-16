package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

/**
 * Awards points per each group of receipt items.
 */
public class ItemCountMultipleRule implements IPointRule {

    /** Points awarded per each group. */
    private final long pointsPerGroup;

    /** Group size. */
    private final long groupSize;

    /**
     * Creates a new item count multiple rule with parameters provided.
     * @param pointsPerGroup points to award per each item group
     * @param groupSize the number of items in a group
     */
    public ItemCountMultipleRule(long pointsPerGroup, long groupSize) {
        this.pointsPerGroup = pointsPerGroup;
        this.groupSize = groupSize;
    }

    /**
     * Awards points if the rule applies.
     * @param receipt receipt entity
     * @return the number of points awarded
     */
    @Override
    public Long compute(ReceiptEntity receipt) {
        return receipt.getItems().size() / groupSize * pointsPerGroup;
    }
}
