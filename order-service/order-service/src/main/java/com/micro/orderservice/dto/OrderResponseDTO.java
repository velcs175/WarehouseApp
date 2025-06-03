package com.micro.orderservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private String productCode;
    private int quantity;
    private String status;
    private double totalPrice;
    private LocalDateTime orderDate;
}
