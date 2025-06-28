package com.example.demo.service;

 
import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment makePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentByReservation(Reservation reservation) {
        return paymentRepository.findByReservation(reservation);
    }
}
