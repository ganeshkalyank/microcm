package com.microcm.order.monitoring;

import java.util.List;

public class SpanResponse {
    private String status;
    private String message;
    private List<Span> data;

    public SpanResponse(String status, String message, List<Span> data) {
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

    public List<Span> getData() {
        return this.data;
    }
}
