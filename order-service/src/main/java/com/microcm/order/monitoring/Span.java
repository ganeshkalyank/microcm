package com.microcm.order.monitoring;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Span {
    private Long spanId;
    private Long responseTime;
    private Long transactionId;
    private LocalDateTime invocationDateTime;
    private String requestedService;
    private String parentService;
}
