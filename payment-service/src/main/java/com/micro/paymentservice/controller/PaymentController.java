package com.micro.paymentservice.controller;

import org.springframework.web.bind.annotation.*;
import com.micro.paymentservice.dto.PaymentRequestDTO;
import com.micro.paymentservice.dto.PaymentResponseDTO;
import com.micro.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

//    @PostMapping("/make")
//    public ResponseEntity<PaymentResponseDTO> makePayment(@RequestBody PaymentRequestDTO requestDTO) {
//        PaymentResponseDTO response = paymentService.makePayment(requestDTO);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    
    @PostMapping("/process")
    public PaymentResponseDTO processPayment(@RequestBody PaymentRequestDTO request) {
        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setTransactionId(UUID.randomUUID().toString());
        response.setTimeStamp(LocalDateTime.now());

        // Dummy business logic
        if (request.getAmount() > 0) {
            response.setPaymentStatus("SUCCESS");
        } else {
            response.setPaymentStatus("FAILED");
        }

        return response;
    }
}
