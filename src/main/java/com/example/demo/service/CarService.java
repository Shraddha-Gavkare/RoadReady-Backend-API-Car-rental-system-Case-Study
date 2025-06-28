package com.example.demo.service;

 
 
import com.example.demo.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car addCar(Car car);
    List<Car> getAllCars();
    Optional<Car> getCarById(Long id);
    void deleteCar(Long id);
    Car updateCar(Car car);
    List<Car> getAvailableCars();
}
