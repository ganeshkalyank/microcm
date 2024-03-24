package com.microcm.entry.consumer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SpanRequest {
    private Long responseTime;
    private Long transactionId;
}
