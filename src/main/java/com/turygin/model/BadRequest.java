package com.turygin.model;

/** Bad request error model. */
public class BadRequest extends Error {

    /** Creates bad request response with a fixed description. */
    public BadRequest() {
        super();
        description = "The receipt is invalid.";
    }
}
