package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String paymentMethod; // e.g., CARD, UPI, NETBANKING

    private String paymentStatus; // SUCCESS, FAILED, PENDING

    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
