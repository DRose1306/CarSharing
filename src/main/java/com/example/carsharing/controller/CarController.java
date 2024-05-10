package com.example.carsharing.controller;

import com.example.carsharing.annotation.AddCar;
import com.example.carsharing.annotation.DeleteCar;
import com.example.carsharing.annotation.ShowCar;
import com.example.carsharing.annotation.UpdateCar;
import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;
import com.example.carsharing.service.interfaces.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/сar")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @ShowCar(path = "/show_car/{id}")
    public Car showCarById(@PathVariable("id") UUID id) {
        return carService.showCar(id);
    }

    @DeleteCar(path = "/delete_car/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCarById(@PathVariable("id") UUID id) {
        return carService.deleteCarById(id);
    }

    @AddCar(path = "/add_car")
    @ResponseStatus(HttpStatus.CREATED)
    public CarAfterCreationDto createCar(@RequestBody CarCreateDto carCreateDto) {
        return carService.addCar(carCreateDto);
    }

    @UpdateCar(path = "/update_car/{id}")
    public Car updateCar(@PathVariable("id") UUID id, @RequestBody CarCreateDto carCreateDto) {
        return carService.updateCarById(id, carCreateDto);
    }
}
