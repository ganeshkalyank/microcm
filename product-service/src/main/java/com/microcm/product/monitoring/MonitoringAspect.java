package com.microcm.product.monitoring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;


@Component
@Aspect
@Slf4j
public class MonitoringAspect {

    @Around("execution(* com.microcm.product.controller.ProductController.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        String uri = "http://localhost:8080/transactions";

        RestTemplate restTemplate = new RestTemplate();

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setRequestedService("product");
        
        ResponseEntity<TransactionResponse> response = restTemplate.postForEntity(uri, transactionRequest , TransactionResponse.class);

        TransactionResponse transactionResponse = response.getBody(); 
        Transaction transaction = transactionResponse.getData().get(0);

        if(response.getStatusCode() == HttpStatus.OK){
            log.info("Succesfully created the transaction ID");   
            log.info("Transaction ID: {}", transaction.getTransactionId());
        }

        String spanURI = "http://localhost:8080/span";
        SpanRequest spanRequest = new SpanRequest();
        spanRequest.setResponseTime(executionTime);
        spanRequest.setTransactionId(transaction.getTransactionId());

        ResponseEntity<SpanResponse> spanRes = restTemplate.postForEntity(spanURI, spanRequest, SpanResponse.class);
        if(response.getStatusCode() == HttpStatus.OK){
            log.info("Succesfully created the Span");   
            SpanResponse spanResponse = spanRes.getBody(); 
            Span span = spanResponse.getData().get(0);
            log.info("Span ID: {}", span.getSpanId()); 
        }


        log.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
        return result;
    }

}
