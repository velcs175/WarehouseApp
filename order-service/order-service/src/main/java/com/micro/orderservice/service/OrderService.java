package com.micro.orderservice.service;

import com.micro.orderservice.client.InventoryClient;
import com.micro.orderservice.model.Order;
import com.micro.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    @Transactional
    public String placeOrder(String productCode, int quantity, double price) {
        boolean isAvailable = inventoryClient.checkStock(productCode, quantity);

        if (isAvailable) {
            Order order = new Order();
            order.setProductCode(productCode);
            order.setQuantity(quantity);
            order.setTotalPrice(price);
            order.setStatus("COMPLETED");
            orderRepository.save(order);

            return "Order placed successfully!";
        } else {
            return "Insufficient stock!";
        }
    }
}
