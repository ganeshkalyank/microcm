package com.microcm.entry.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.microcm.entry.models.Span;
import com.microcm.entry.repositories.SpanRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SpanConsumer {
    private final SpanRepository spanRepository;

    public SpanConsumer(SpanRepository spanRepository) {
        this.spanRepository = spanRepository;
    }

    @RabbitListener(queues ="transmission-queue")
    public void consumeSpan(String data){
        // Span span = new Span();
        // span.setResponseTime(spanRequest.getResponseTime());
        // span.setTransactionId(spanRequest.getTransactionId());
        // spanRepository.save(span);
        log.info("Span recieved: {}", data);
    }
}
