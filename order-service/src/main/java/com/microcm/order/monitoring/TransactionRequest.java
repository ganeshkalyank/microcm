package com.microcm.order.monitoring;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TransactionRequest {
    private String requestedService;
}
