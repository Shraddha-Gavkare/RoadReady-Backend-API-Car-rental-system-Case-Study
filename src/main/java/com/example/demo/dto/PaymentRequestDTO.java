package com.example.demo.dto;

 

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    private Long reservationId;
    private BigDecimal amount;
    private String paymentMethod;
}
