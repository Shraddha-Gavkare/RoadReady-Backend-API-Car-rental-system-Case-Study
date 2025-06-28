package com.example.demo.controller;

import com.example.demo.dto.ReservationRequestDTO;
import com.example.demo.dto.ReservationResponseDTO;
import com.example.demo.entity.Car;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import com.example.demo.service.CarService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationController {

 
@Autowired
private ReservationService reservationService;

@Autowired
private UserService userService;

@Autowired
private CarService carService;

@PostMapping
public ResponseEntity<?> createReservation(@RequestBody ReservationRequestDTO dto, Principal principal) {
    User user = userService.getUserByEmail(principal.getName()).orElse(null);
    Car car = carService.getCarById(dto.getCarId()).orElse(null);

    if (user == null || car == null || !car.isAvailable()) {
        return ResponseEntity.badRequest().body("Invalid user or car not available");
    }

    Reservation reservation = Reservation.builder()
            .user(user)
            .car(car)
            .startDate(dto.getStartDate())
            .endDate(dto.getEndDate())
            .pickupLocation(dto.getPickupLocation())
            .dropoffLocation(dto.getDropoffLocation())
            .status("PENDING")
            .build();

    car.setAvailable(false);
    carService.updateCar(car);

    Reservation saved = reservationService.createReservation(reservation);

    ReservationResponseDTO responseDTO = ReservationResponseDTO.builder()
            .id(saved.getId())
            .carId(saved.getCar().getId())
            .carBrand(saved.getCar().getBrand())
            .carModel(saved.getCar().getModel())
            .startDate(saved.getStartDate())
            .endDate(saved.getEndDate())
            .pickupLocation(saved.getPickupLocation())
            .dropoffLocation(saved.getDropoffLocation())
            .status(saved.getStatus())
            .build();

    return ResponseEntity.ok(responseDTO);
}

@GetMapping
public ResponseEntity<List<ReservationResponseDTO>> getUserReservations(Principal principal) {
    User user = userService.getUserByEmail(principal.getName()).orElse(null);
    List<Reservation> reservations = reservationService.getReservationsByUser(user);
    List<ReservationResponseDTO> responseList = reservations.stream().map(saved -> ReservationResponseDTO.builder()
            .id(saved.getId())
            .carId(saved.getCar().getId())
            .carBrand(saved.getCar().getBrand())
            .carModel(saved.getCar().getModel())
            .startDate(saved.getStartDate())
            .endDate(saved.getEndDate())
            .pickupLocation(saved.getPickupLocation())
            .dropoffLocation(saved.getDropoffLocation())
            .status(saved.getStatus())
            .build()).collect(Collectors.toList());

    return ResponseEntity.ok(responseList);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
    reservationService.cancelReservation(id);
    return ResponseEntity.noContent().build();
}
}