package com.micro.orderservice.controller;

import com.micro.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public String placeOrder(
            @RequestParam String productCode,
            @RequestParam int quantity,
            @RequestParam double price) {
        return orderService.placeOrder(productCode, quantity, price);
    }
}
