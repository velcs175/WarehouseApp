package com.micro.orderservice.controller;

import com.micro.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;
import com.micro.orderservice.dto.OrderRequestDTO;
import com.micro.orderservice.dto.OrderResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
    	OrderResponseDTO response= orderService.placeOrder(orderRequestDTO);
    	return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
