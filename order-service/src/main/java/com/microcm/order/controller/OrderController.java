package com.microcm.order.controller;

import com.microcm.order.dto.OrderRequest;
import com.microcm.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor

public class OrderController {
    @Autowired
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed.";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getAllOrders(){
        return "Orders";
    }
}
