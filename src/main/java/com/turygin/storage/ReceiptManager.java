package com.turygin.storage;

import com.turygin.model.Receipt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReceiptManager {

    private final Map<UUID, Receipt> receipts = new HashMap<>();

    public void addReceipt(UUID id, Receipt receipt) {
        receipts.put(id, receipt);
    }

    public Receipt getReceipt(UUID id) {
        return receipts.get(id);
    }
}
