package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

import java.math.BigDecimal;

public class TotalMultipleRule implements IPointRule {

    private final long points;
    private final BigDecimal multipleOf;

    public TotalMultipleRule(long points, BigDecimal multipleOf) {
        this.points = points;
        this.multipleOf = multipleOf;
    }

    @Override
    public Long compute(ReceiptEntity receipt) {
        BigDecimal total = receipt.getTotal();
        BigDecimal remainder = total.remainder(multipleOf);
        return remainder.compareTo(BigDecimal.ZERO) == 0 ? points : 0L;
    }
}
