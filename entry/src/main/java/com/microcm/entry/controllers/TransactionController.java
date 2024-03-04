package com.microcm.entry.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microcm.entry.models.ServiceResponse;
import com.microcm.entry.models.Transaction;
import com.microcm.entry.repositories.TransactionRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;

    TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transactions")
    public ResponseEntity<ServiceResponse> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return new ResponseEntity<ServiceResponse>(
            new ServiceResponse(
                "success",
                "Transactions retrieved!",
                transactions
            ),
            HttpStatus.OK
        );
    }

    @PostMapping("/transactions")
    public ResponseEntity<ServiceResponse> createTransaction(@RequestBody Transaction t) {
        try {
            transactionRepository.save(t);
            return new ResponseEntity<ServiceResponse>(
                new ServiceResponse(
                    "success", 
                    "New transaction created!", 
                    null
                ),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<ServiceResponse>(
                new ServiceResponse(
                    "error",
                    e.getMessage(),
                    null
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
