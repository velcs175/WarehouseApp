package com.micro.orderservice.service;

import com.micro.orderservice.client.InventoryClient;
import com.micro.orderservice.client.PaymentClient;
import com.micro.orderservice.model.Order;
import com.micro.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.micro.orderservice.dto.OrderRequestDTO;
import com.micro.orderservice.dto.OrderResponseDTO;
import com.micro.common.dto.PaymentRequestDTO;
import com.micro.common.dto.PaymentResponseDTO;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient, PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.paymentClient = paymentClient;
    }

    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO requestDTO) {
        boolean isAvailable = inventoryClient.checkStock(requestDTO.getProductCode(), requestDTO.getQuantity());
        
        if(!isAvailable) {
        	throw new RuntimeException("Product not in stock!");
        }

        
            Order order = new Order();
            order.setProductCode(requestDTO.getProductCode());
            order.setQuantity(requestDTO.getQuantity());
            order.setTotalPrice(requestDTO.getPrice() * requestDTO.getQuantity());
            order.setStatus("COMPLETED");
            order.setOrderDate(LocalDateTime.now());
            Order savedOrder= orderRepository.save(order);
            
            PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
            paymentRequest.setProductCode(order.getProductCode());
            paymentRequest.setAmount(order.getTotalPrice());
            paymentRequest.setPaymentMethod("UPI");
            
            try {
                PaymentResponseDTO paymentResponse = paymentClient.processPayment(paymentRequest);
                order.setStatus(paymentResponse.getStatus().equals("SUCCESS") ? "COMPLETED" : "FAILED");
            } catch (Exception e) {
                order.setStatus("FAILED");
            }
            
            orderRepository.save(order);
            return new OrderResponseDTO(order.getId(),order.getProductCode(),order.getQuantity(), order.getStatus(), order.getTotalPrice(),
            		order.getOrderDate());
//            return new OrderResponseDTO(
//                    savedOrder.getId(),
//                    savedOrder.getProductCode(),
//                    savedOrder.getQuantity(),
//                    savedOrder.getTotalPrice(),
//                    savedOrder.getStatus(),
//                    savedOrder.getOrderDate()
//            );
        
    }
}
