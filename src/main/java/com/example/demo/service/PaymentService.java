package com.example.demo.service;


import com.example.demo.entity.*;
import com.example.demo.entity.Reservation;

import java.util.Optional;

public interface PaymentService {
    Payment makePayment(Payment payment);
    Optional<Payment> getPaymentByReservation(Reservation reservation);
}
