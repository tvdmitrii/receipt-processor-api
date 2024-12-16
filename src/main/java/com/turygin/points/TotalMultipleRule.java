package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

import java.math.BigDecimal;

/**
 * Awards points if receipt total is a multiple of a certain amount.
 */
public class TotalMultipleRule implements IPointRule {

    /** Number of points to award. */
    private final long points;

    /** The value that the total must be a multiple of. */
    private final BigDecimal multipleOf;

    /**
     * Creates a new total multiple rule with parameters provided.
     * @param points the number of points to award
     * @param multipleOf the value that the receipt total must be a multiple of
     */
    public TotalMultipleRule(long points, BigDecimal multipleOf) {
        this.points = points;
        this.multipleOf = multipleOf;
    }

    /**
     * Awards points if the rule applies.
     * @param receipt receipt entity
     * @return the number of points awarded
     */
    @Override
    public Long compute(ReceiptEntity receipt) {
        BigDecimal total = receipt.getTotal();
        BigDecimal remainder = total.remainder(multipleOf);

        // It is better to use compareTo instead of equals to avoid issues with BigDecimal scale.
        return remainder.compareTo(BigDecimal.ZERO) == 0 ? points : 0L;
    }
}
