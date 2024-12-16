package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

/**
 * Common interface for independent rules that compute points based on receipt properties.
 * Pipe of a Pipeline pattern.
 */
public interface IPointRule {
    /**
     * Awards points if the rule applies.
     * @param receipt receipt entity
     * @return the number of points awarded
     */
    public Long compute(ReceiptEntity receipt);
}
