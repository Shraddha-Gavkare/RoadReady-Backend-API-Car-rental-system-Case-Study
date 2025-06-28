package com.example.demo.dto;


import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {
private Long id;
private Long carId;
private String carBrand;
private String carModel;
private LocalDate startDate;
private LocalDate endDate;
private String pickupLocation;
private String dropoffLocation;
private String status;
}