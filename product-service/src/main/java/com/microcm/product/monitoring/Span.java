package com.microcm.product.monitoring;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Span {
    private Long spanId;
    private Long responseTime;
    private Long transactionId;
}
