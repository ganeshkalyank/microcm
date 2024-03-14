package com.microcm.product.monitoring;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SpanRequest {
    private Long responseTime;
    private Long transactionId;
}
