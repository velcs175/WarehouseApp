package com.micro.orderservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private String productCode;
    private int quantity;
    private double price;
}
