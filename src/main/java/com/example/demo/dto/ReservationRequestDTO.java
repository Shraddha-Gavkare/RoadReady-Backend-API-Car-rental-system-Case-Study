package com.example.demo.dto;


 

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {
private Long carId;
private LocalDate startDate;
private LocalDate endDate;
private String pickupLocation;
private String dropoffLocation;
}