package com.example.carsharing.service;

import com.example.carsharing.entity.Car;

import java.util.UUID;

public interface CarService {

    Car getCarById(UUID id);
}
