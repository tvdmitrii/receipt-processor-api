package com.turygin.model;

import java.util.UUID;

/**
 * Receipt ID response model.
 */
public class ReceiptId {

    /** Receipt UUID. */
    public UUID receiptId;

    /**
     * Creates a new receipt id model.
     * @param receiptId receipt UUID
     */
    public ReceiptId(UUID receiptId) {
        this.receiptId = receiptId;
    }
}
