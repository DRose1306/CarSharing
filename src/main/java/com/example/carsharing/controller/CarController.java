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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
@Tag(name = "Car Controller", description = "Operations pertaining to cars")
public class CarController {
    private final CarService carService;

    @Operation(summary = "Show car by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    @ShowCar(path = "/show_car/{id}")
    public Car showCarById(@PathVariable("id") @UuidFormatChecker String id) {
        return carService.showCar(UUID.fromString(id));
    }

    @Operation(summary = "Delete car by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    @DeleteCar(path = "/delete_car/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCarById(@PathVariable("id") @UuidFormatChecker String id) {
        return carService.deleteCarById(UUID.fromString(id));
    }

    @Operation(summary = "Add a new car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @AddCar(path = "/add_car")
    @ResponseStatus(HttpStatus.CREATED)
    public CarAfterCreationDto createCar(@RequestBody CarCreateDto carCreateDto) {
        return carService.addCar(carCreateDto);
    }

    @Operation(summary = "Update car by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car updated"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    @UpdateCar(path = "/update_car/{id}")
    public Car updateCar(@PathVariable("id") @UuidFormatChecker String id, @RequestBody @Valid Car car) {
        return carService.updateCarById(UUID.fromString(id), car);
    }
}
