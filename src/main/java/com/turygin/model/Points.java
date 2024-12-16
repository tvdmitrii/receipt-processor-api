package com.turygin.model;

/**
 * Point response model.
 */
public class Points {

    /** Number of points awarded. */
    public long points;

    /**
     * Creates point model.
     * @param points number of points awarded
     */
    public Points(long points) {
        this.points = points;
    }
}
