package com.microcm.entry;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long transaction_id;
    private String requested_service;

    public Long getTransaction_id() {
        return this.transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getRequested_service() {
        return this.requested_service;
    }

    public void setRequested_service(String requested_service) {
        this.requested_service = requested_service;
    }

    @Override
    public String toString() {
        return "{transaction_id: " + this.transaction_id + ", requested_service:" + this.requested_service + "}";
    }
}
