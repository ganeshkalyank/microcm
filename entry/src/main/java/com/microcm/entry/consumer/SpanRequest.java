package com.microcm.entry.consumer;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SpanRequest {
    private Long responseTime;
    private Long transactionId;
    private LocalDateTime invocationDateTime;
    private String requestedService;
    private String parentService;
}
