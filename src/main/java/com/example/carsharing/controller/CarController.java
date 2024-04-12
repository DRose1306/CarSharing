package com.example.carsharing.controller;

import com.example.carsharing.entity.Car;
import com.example.carsharing.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/get/{id}")
    public Car getCarById(@PathVariable("id") UUID id) {
        return carService.getCarById(id);
    }
}
