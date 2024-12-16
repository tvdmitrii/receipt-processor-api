package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregates independent point rules to compute the total number of points awarded for receipt by all rules.
 * Pipeline of a Pipeline pattern.
 */
public class PointPipeline {

    /** List of point rules. */
    private final List<IPointRule> rules = new ArrayList<>();

    /**
     * Adds a new independent rule to the pipeline.
     * @param rule new point rule to add
     */
    public void addRule(IPointRule rule) {
        rules.add(rule);
    }

    /**
     * Computes the total number of points awarded for the receipt by all the rules.
     * @param receipt receipt to process
     * @return the total number of points awarded
     */
    public Long compute(ReceiptEntity receipt) {
        long result = 0L;

        for (IPointRule rule : rules) {
            result += rule.compute(receipt);
        }

        return result;
    }
}
