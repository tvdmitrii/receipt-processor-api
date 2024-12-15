package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

public interface IPointRule {
    public Long compute(ReceiptEntity receipt);
}
