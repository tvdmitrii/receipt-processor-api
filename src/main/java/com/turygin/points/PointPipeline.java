package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

import java.util.ArrayList;
import java.util.List;

public class PointPipeline {
    private final List<IPointRule> rules = new ArrayList<>();

    public void addRule(IPointRule rule) {
        rules.add(rule);
    }

    public Long compute(ReceiptEntity receipt) {
        long result = 0;
        for (IPointRule rule : rules) {
            result += rule.compute(receipt);
        }
        return result;
    }
}
