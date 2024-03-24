package com.microcm.product.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.microcm.product.monitoring.SpanRequest;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    public Producer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }    
    public void sendSpan(SpanRequest spanRequest){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "", spanRequest);
    }
}
