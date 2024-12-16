package com.turygin.points;

import com.turygin.storage.ReceiptEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Awards points per each character in receipt's retailer field that matches specified regex.
 */
public class RetailerCharCountRule implements IPointRule {

    /** Pattern for finding characters that are worth points. */
    private final Pattern pattern;

    /** Number of points awarded per character. */
    private final long pointsPerChar;

    /**
     * Creates a new retailer character count rule with parameters provided.
     * @param regex regex defining the characters that award points
     * @param pointsPerChar number of points to award per each character found
     */
    public RetailerCharCountRule(String regex, long pointsPerChar) {
        this.pattern = Pattern.compile(regex);
        this.pointsPerChar = pointsPerChar;
    }

    /**
     * Awards points if the rule applies.
     * @param receipt receipt entity
     * @return the number of points awarded
     */
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
