package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RetailerCharCountRule implements IPointRule {

    private final Pattern pattern;
    private final long pointsPerChar;

    public RetailerCharCountRule(String regex, long pointsPerChar) {
        this.pattern = Pattern.compile(regex);
        this.pointsPerChar = pointsPerChar;
    }

    @Override
    public Long compute(ReceiptEntity receipt) {
        Matcher matcher = pattern.matcher(receipt.getRetailer());
        long count = 0;
        while (matcher.find()) {
            count++;
        }
        return count*pointsPerChar;
    }
}
