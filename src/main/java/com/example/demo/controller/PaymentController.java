package com.example.demo.controller;

import com.example.demo.dto.*;
 import com.example.demo.entity.*;

import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody PaymentRequestDTO dto) {
        Reservation reservation = reservationService.getReservationById(dto.getReservationId()).orElse(null);

        if (reservation == null) {
            return ResponseEntity.badRequest().body("Invalid reservation ID");
        }

        Payment payment = Payment.builder()
                .reservation(reservation)
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .paymentStatus("SUCCESS")
                .paymentDate(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(paymentService.makePayment(payment));
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<?> getPaymentByReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId).orElse(null);

        if (reservation == null) {
            return ResponseEntity.badRequest().body("Invalid reservation ID");
        }

        return ResponseEntity.ok(paymentService.getPaymentByReservation(reservation));
    }
}
