package com.micro.paymentservice.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private Long orderId;
    private Double amount;
    private String paymentMethod;
    private String productCode; 
}
