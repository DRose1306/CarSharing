package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Car;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car getCarById(UUID id) {
        return carRepository.getCarByCarId(id);
    }
}
