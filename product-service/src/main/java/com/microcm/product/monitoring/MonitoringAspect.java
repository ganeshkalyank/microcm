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

        ResponseEntity<String> response = restTemplate.postForEntity(uri, transactionRequest , String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            log.info("Succesfully created the transaction ID");
        }

        log.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
        return result;
    }

}
