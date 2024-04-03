package com.microcm.entry.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microcm.entry.models.Span;
import com.microcm.entry.repositories.SpanRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class SpanConsumer {
    private final SpanRepository spanRepository;
    private final ObjectMapper objectMapper;


    @RabbitListener(queues ="transmission-queue")
    public void consumeSpan(String data) throws Exception{
        SpanRequest spanRequest = objectMapper.readValue(data, SpanRequest.class);
        Span span = new Span();
        span.setResponseTime(spanRequest.getResponseTime());
        span.setTransactionId(spanRequest.getTransactionId());
        span.setInvocationDateTime(spanRequest.getInvocationDateTime());
        span.setParentService(spanRequest.getParentService());
        span.setRequestedService(spanRequest.getRequestedService());
        spanRepository.save(span);
        log.info("Span recieved: {}", data);
    }
}
