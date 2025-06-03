package com.micro.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;
    private Integer quantity;
    private Double totalPrice;
    private String status;  // PENDING, COMPLETED, FAILED

    private LocalDateTime orderDate;
}
