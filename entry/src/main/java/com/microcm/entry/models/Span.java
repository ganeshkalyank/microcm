package com.microcm.entry.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spans")
public class Span {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long spanId;
    private Long responseTime;
    private Long transactionId;
}
