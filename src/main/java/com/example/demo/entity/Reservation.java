package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

 
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private LocalDate startDate;
private LocalDate endDate;
private String status; // PENDING, CONFIRMED, CANCELLED
private String pickupLocation;
private String dropoffLocation;

@ManyToOne
@JoinColumn(name = "user_id")
@JsonIgnore
private User user;

@ManyToOne
@JoinColumn(name = "car_id")
@JsonIgnore
private Car car;
}