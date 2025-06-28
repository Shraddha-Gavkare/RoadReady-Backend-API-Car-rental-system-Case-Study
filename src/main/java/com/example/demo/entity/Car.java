package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

 
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String brand;
private String model;
private int year;
private String color;
private String type; // SUV, Sedan, etc.
private BigDecimal pricePerDay;
private boolean available = true;
private String licensePlate;

@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
@JsonIgnore
private List<Reservation> reservations;
}