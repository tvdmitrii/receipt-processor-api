package com.turygin.model;

public class BadRequest extends Error {

    public BadRequest() {
        super();
        description = "The receipt is invalid.";
    }
}
