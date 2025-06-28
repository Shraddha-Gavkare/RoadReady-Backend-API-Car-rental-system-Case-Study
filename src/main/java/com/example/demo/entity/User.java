package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

@Column(unique = true, nullable = false)
private String email;

private String password;

private String role; // ADMIN or CUSTOMER

private String phone;

private String address;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
@JsonIgnore
private List<Reservation> reservations;
}