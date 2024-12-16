package com.turygin.storage;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Provides receipt storage and retrieval using an internal hash map.
 */
public class ReceiptManager {

    /** Hash map with receipt UUID as key and receipt entity as a value. */
    private final Map<UUID, ReceiptEntity> receipts = new HashMap<>();

    /**
     * Adds new receipt to storage.
     * @param id receipt UUID
     * @param receipt receipt entity to store
     */
    public void addReceipt(UUID id, ReceiptEntity receipt) {
        receipts.put(id, receipt);
    }

    /**
     * Gets receipt by UUID.
     * @param id receipt UUID
     * @return receipt entity if exists, null otherwise
     */
    public ReceiptEntity getReceipt(UUID id) {
        return receipts.get(id);
    }
}
