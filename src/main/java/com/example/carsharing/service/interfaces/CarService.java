package com.example.carsharing.service.interfaces;

import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;

import java.util.UUID;

public interface CarService {
    Car getCarById(UUID id);
    void deleteCarById(UUID id);
    CarAfterCreationDto createCar(CarCreateDto carCreateDto);
    Car updateCarById(UUID id, CarCreateDto carCreateDto);
}
