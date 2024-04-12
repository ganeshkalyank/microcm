package com.microcm.order.monitoring;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Long transactionId;
    private String requestedService;
}
