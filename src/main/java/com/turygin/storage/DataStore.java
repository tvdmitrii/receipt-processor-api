package com.turygin.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataStore {

    private static final Logger LOG = LogManager.getLogger(DataStore.class);
    private static DataStore instance = null;

    private final ReceiptManager receiptManager;

    private DataStore() {
        receiptManager = new ReceiptManager();
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }

        return instance;
    }

    public ReceiptManager getReceiptManager() {
        return receiptManager;
    }
}
