package com.micro.paymentservice.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String paymentMethod; // e.g., CARD, UPI, COD
    private Double amount;
    private String paymentStatus; // SUCCESS or FAILED

    private LocalDateTime paymentDate = LocalDateTime.now();
}
