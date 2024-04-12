package com.microcm.order.controller;

import com.microcm.order.dto.OrderRequest;
import com.microcm.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully.";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getAllOrder(){
        return "Orders";
    }
}
