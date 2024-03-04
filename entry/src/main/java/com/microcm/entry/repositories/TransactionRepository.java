package com.microcm.entry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microcm.entry.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
