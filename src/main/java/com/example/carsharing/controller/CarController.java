package com.example.carsharing.controller;

import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;
import com.example.carsharing.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/сar")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/get_car/{id}")
    public Car getCarById(@PathVariable("id") UUID id) {
        return carService.getCarById(id);
    }

    @DeleteMapping("/delete_car/{id}")
    public void deleteCarById(@PathVariable("id") UUID id) {
        carService.deleteCarById(id);
    }

    @PostMapping("/create_car")
    @ResponseStatus(HttpStatus.CREATED)//TODO
    public CarAfterCreationDto createCar(@RequestBody CarCreateDto carCreateDto) {
        return carService.createCar(carCreateDto);
    }

    @PutMapping("/update_car/{id}")
    public Car updateCar(@PathVariable("id") UUID id, @RequestBody Car car) {
        return carService.updateCarById(id, car);
    }
}
