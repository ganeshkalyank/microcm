package com.microcm.entry.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Span {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long span_id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    private Long response_time;

    public Long getSpan_id() {
        return span_id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Long getResponse_time() {
        return response_time;
    }

    public void setResponse_time(Long response_time) {
        this.response_time = response_time;
    }

    public void setSpan_id(Long span_id) {
        this.span_id = span_id;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
