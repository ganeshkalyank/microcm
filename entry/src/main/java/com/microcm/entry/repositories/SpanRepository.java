package com.microcm.entry.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microcm.entry.models.Span;

public interface SpanRepository extends JpaRepository<Span, Long> {
    List<Span> findByTransactionId(Long transactionId);
}
