package com.microcm.entry.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.microcm.entry.models.Span;
import com.microcm.entry.models.SpanResponse;
import com.microcm.entry.repositories.SpanRepository;

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

    @GetMapping("/span/{transaction_id}")
    public ResponseEntity<SpanResponse> getSpansByTransaction(@PathVariable Long transaction_id) {
        List<Span> spans = spanRepository.findByTransaction_id(transaction_id);
        return new ResponseEntity<SpanResponse>(
            new SpanResponse(
                "success",
                "Spans retrieved successfully!",
                spans
            ),
            HttpStatus.OK
        );
    }
    
    @PostMapping("/span/{transaction_id}")
    public ResponseEntity<SpanResponse> createSpan(@RequestBody Span s, @PathVariable Long transaction_id) {
        try {
            spanRepository.save(s);
            return new ResponseEntity<SpanResponse>(
                new SpanResponse(
                    "success",
                    "Span created successfully!",
                    null
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
