package com.micro.paymentservice.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private String paymentMethod;
    private String transactionId;
    private Double amount;
    private String status;
    private LocalDateTime timeStamp;
}
