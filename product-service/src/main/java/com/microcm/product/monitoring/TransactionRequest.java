package com.microcm.product.monitoring;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TransactionRequest {
    private String requestedService;
}
