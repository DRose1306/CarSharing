package com.example.carsharing.service.interfaces;

import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;

import java.util.UUID;

public interface CarService {
    Car showCar(UUID id);
    String deleteCarById(UUID id);
    CarAfterCreationDto addCar(CarCreateDto carCreateDto);
    Car updateCarById(UUID id, CarCreateDto carCreateDto);
}
