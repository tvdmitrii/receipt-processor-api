package com.turygin.storage;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReceiptManager {

    private final Map<UUID, ReceiptEntity> receipts = new HashMap<>();

    public void addReceipt(UUID id, ReceiptEntity receipt) {
        receipts.put(id, receipt);
    }

    public ReceiptEntity getReceipt(UUID id) {
        return receipts.get(id);
    }
}
