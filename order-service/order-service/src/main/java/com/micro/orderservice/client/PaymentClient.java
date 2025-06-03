package com.micro.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.micro.common.dto.PaymentRequestDTO;
import com.micro.common.dto.PaymentResponseDTO;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {

    @PostMapping("/payment/process")
    PaymentResponseDTO processPayment(@RequestBody PaymentRequestDTO request);
}
