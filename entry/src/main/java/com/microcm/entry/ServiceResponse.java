package com.microcm.entry;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceResponse {
    private String status;
    private String message;
    private List<Transaction> data;

    public ServiceResponse(String status, String message, List<Transaction> data) {
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
