package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Car;
import com.example.carsharing.exception.CarNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.service.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car getCarById(UUID id) {
        Car car = carRepository.getCarByCarId(id);
        if (car == null) {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }
        return car;
    }

    @Override
    @Transactional
    public void deleteCarById(UUID id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }
        carRepository.deleteCarByCarId(id);
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.saveAndFlush(car);
    }

    @Override
    @Transactional
    public Car updateCarById(UUID id, Car updatedCar) {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }
        car.setYearOfRelease(updatedCar.getYearOfRelease());
        car.setLicensePlate(updatedCar.getLicensePlate());
        car.setStatus(updatedCar.getStatus());
        car.setBrand(updatedCar.getBrand());

        return carRepository.saveAndFlush(car);
    }
}
