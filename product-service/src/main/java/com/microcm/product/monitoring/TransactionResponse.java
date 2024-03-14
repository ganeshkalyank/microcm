package com.microcm.product.monitoring;

import java.util.List;

public class TransactionResponse {
    private String status;
    private String message;
    private List<Transaction> data;

    public TransactionResponse(String status, String message, List<Transaction> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public List<Transaction> getData() {
        return this.data;
    }
}
