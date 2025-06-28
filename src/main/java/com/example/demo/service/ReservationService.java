package com.example.demo.service;

 
import com.example.demo.entity.*;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Optional<Reservation> getReservationById(Long id);
    List<Reservation> getReservationsByUser(User user);
    void cancelReservation(Long id);
}
