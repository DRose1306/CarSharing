package com.example.carsharing.controller;

import com.example.carsharing.annotation.AddCar;
import com.example.carsharing.annotation.DeleteCar;
import com.example.carsharing.annotation.ShowCar;
import com.example.carsharing.annotation.UpdateCar;
import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;
import com.example.carsharing.service.interfaces.CarService;
import com.example.carsharing.validation.annotation.UuidFormatChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    @ShowCar(path = "/show_car/{id}")
    public Car showCarById(@PathVariable("id") @UuidFormatChecker String id) {
        return carService.showCar(UUID.fromString(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteCar(path = "/delete_car/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCarById(@PathVariable("id") @UuidFormatChecker String id) {
        return carService.deleteCarById(UUID.fromString(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @AddCar(path = "/add_car")
    @ResponseStatus(HttpStatus.CREATED)
    public CarAfterCreationDto createCar(@RequestBody CarCreateDto carCreateDto) {
        return carService.addCar(carCreateDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @UpdateCar(path = "/update_car/{id}")
    public Car updateCar(@PathVariable("id") @UuidFormatChecker String id, @RequestBody @Valid Car car) {
        return carService.updateCarById(UUID.fromString(id), car);
    }
}