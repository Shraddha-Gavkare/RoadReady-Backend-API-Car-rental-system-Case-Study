package com.example.demo.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarDTO {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String color;
    private String type;
    private BigDecimal pricePerDay;
    private boolean available;
    private String licensePlate;
}
