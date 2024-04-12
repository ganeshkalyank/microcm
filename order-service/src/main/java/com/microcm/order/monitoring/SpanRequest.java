package com.microcm.order.monitoring;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SpanRequest {
    private Long responseTime;
    private Long transactionId;
    private LocalDateTime invocationDateTime;
    private String requestedService;
    private String parentService;
}
