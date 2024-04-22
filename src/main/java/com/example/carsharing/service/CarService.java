package com.example.carsharing.service;

import com.example.carsharing.entity.Car;

import java.util.UUID;

public interface CarService {
    Car getCarById(UUID id);
    void deleteCarById(UUID id);
    Car createCar(Car car);
    Car updateCarById(UUID id, Car updatedCar);
}
