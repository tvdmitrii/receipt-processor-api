package com.turygin.model;

public class NotFound extends Error {
    public NotFound() {
        super();
        description = "No receipt found for that ID.";
    }
}
