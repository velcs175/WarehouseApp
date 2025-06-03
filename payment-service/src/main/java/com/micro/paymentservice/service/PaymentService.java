package com.micro.paymentservice.service;

import com.micro.paymentservice.model.Payment;
import com.micro.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.micro.paymentservice.dto.PaymentRequestDTO;
import com.micro.paymentservice.dto.PaymentResponseDTO;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentService {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;
    

    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
    	logger.info("Received payment request: {}", request);
        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());

        // Simulate payment processing logic
        boolean isPaymentSuccessful = new Random().nextBoolean();

        if (isPaymentSuccessful) {
            payment.setPaymentStatus("SUCCESS");
        } else {
            payment.setPaymentStatus("FAILED");
        }

        paymentRepository.save(payment);

        return new PaymentResponseDTO(
                payment.getPaymentMethod(),
                UUID.randomUUID().toString(),
                payment.getAmount(),
                payment.getPaymentStatus().equals("SUCCESS") ?
                        "Payment processed successfully" :
                        "Payment failed, please try again",
                LocalDateTime.now()
        );
        /*payment.getPaymentStatus(),
        UUID.randomUUID().toString(),
        payment.getAmount(),
        payment.getPaymentStatus(),
        LocalDateTime.now();*/
    }
}
