package com.microcm.entry.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.microcm.entry.models.Span;
import com.microcm.entry.models.SpanResponse;
import com.microcm.entry.repositories.SpanRepository;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class SpanController {

    private final SpanRepository spanRepository;

    public SpanController(SpanRepository spanRepository) {
        this.spanRepository = spanRepository;
    }

    @GetMapping("/span")
    public ResponseEntity<SpanResponse> getAllSpans() {
        List<Span> spans = spanRepository.findAll();
        return new ResponseEntity<SpanResponse>(
            new SpanResponse(
                "success",
                "Spans retrieved successfully!",
                spans
            ),
            HttpStatus.OK
        );
    }
    

    @GetMapping("/span/{transactionId}")
    public ResponseEntity<SpanResponse> getSpansByTransaction(@PathVariable Long transactionId) {
        List<Span> spans = spanRepository.findByTransactionId(transactionId);
        return new ResponseEntity<SpanResponse>(
            new SpanResponse(
                "success",
                "Spans retrieved successfully!",
                spans
            ),
            HttpStatus.OK
        );
    }
    
    @PostMapping("/span")
    public ResponseEntity<SpanResponse> createSpan(@RequestBody Span s) {
        try {
            List<Span> newspan = Arrays.asList(spanRepository.save(s));
            return new ResponseEntity<SpanResponse>(
                new SpanResponse(
                    "success",
                    "Span created successfully!",
                    newspan
                ),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<SpanResponse>(
                new SpanResponse(
                    "success",
                    "Span creation failed!",
                    null
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    
    
}
