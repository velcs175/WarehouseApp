package com.micro.paymentservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.micro.paymentservice.dto.PaymentRequestDTO;
import com.micro.paymentservice.dto.PaymentResponseDTO;
import com.micro.paymentservice.model.Payment;
import com.micro.paymentservice.repository.PaymentRepository;
import com.micro.paymentservice.service.PaymentService;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PaymentServiceApplicationTests {
	
	@InjectMocks
	private PaymentService paymentService;
	
	@Mock
	private PaymentRepository paymentRepository;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testMakePayment() {
		PaymentRequestDTO requestDTO=new PaymentRequestDTO();
		requestDTO.setOrderId(101L);
		requestDTO.setAmount(250.00);
		requestDTO.setPaymentMethod("CARD");
		
		PaymentResponseDTO response=paymentService.processPayment(requestDTO);
		
		assertNotNull(response);
		assertEquals(requestDTO.getAmount(), response.getAmount());
		assertNotNull(response.getTransactionId());
		assertNotNull(response.getTimeStamp());
		//assertTrue(response.getPaymentStatus().equals("SUCCESS") || response.getPaymentStatus().equals("FAILED"));
		
		verify(paymentRepository, times(1)).save(any(Payment.class));
		
	}

}
