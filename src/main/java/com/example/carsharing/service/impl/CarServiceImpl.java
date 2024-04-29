package com.example.carsharing.service.impl;

import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;
import com.example.carsharing.exception.CarAlreadyExistException;
import com.example.carsharing.exception.CarNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.mapper.CarMapper;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.service.interfaces.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Car getCarById(UUID id) {
        Car car = carRepository.getCarByCarId(id);
        if (car == null) {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }
        return car;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteCarById(UUID id) {
        carRepository.findById(id).orElseThrow(() -> new CarNotExistException(ErrorMessage.CAR_NOT_EXIST));
        carRepository.deleteCarByCarId(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CarAfterCreationDto createCar(CarCreateDto carCreateDto) {
        Car car = carRepository.findByLicensePlate(carCreateDto.getLicensePlate());
        if (car != null) {
            throw new CarAlreadyExistException(ErrorMessage.CAR_ALREADY_EXIST);
        }
        Car entity = carMapper.toEntity(carCreateDto);
        Car carAfterCreation = carRepository.save(entity);
        return carMapper.toDto(carAfterCreation);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Car updateCarById(UUID id, CarCreateDto carCreateDto) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotExistException(ErrorMessage.CAR_NOT_EXIST));
        car.setYearOfRelease(carCreateDto.getYearOfRelease());
        car.setLicensePlate(carCreateDto.getLicensePlate());
        car.setBrand(carCreateDto.getBrand());

        return carRepository.saveAndFlush(car);
    }
}
