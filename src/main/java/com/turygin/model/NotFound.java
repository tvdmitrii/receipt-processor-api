package com.turygin.model;

/** Not found error model. */
public class NotFound extends Error {
    /** Creates not found response with a fixed description. */
    public NotFound() {
        super();
        description = "No receipt found for that ID.";
    }
}
