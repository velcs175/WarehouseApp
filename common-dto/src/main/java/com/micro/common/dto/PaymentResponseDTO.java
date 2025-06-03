package com.micro.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private String paymentMethod;
    private String transactionId;
    private Double amount;
    private String status;
    private LocalDateTime timestamp;
}
