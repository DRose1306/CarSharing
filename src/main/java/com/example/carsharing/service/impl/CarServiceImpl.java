package com.example.carsharing.service.impl;

import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.enums.CarStatus;
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

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Car showCar(UUID id) {
        Car car = carRepository.getCarByCarId(id);
        if (car == null) {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }
        return car;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String deleteCarById(UUID id) {
        Car car = carRepository.getCarByCarId(id);
        if (car != null){
            carRepository.deleteById(id);
            return "Car with this ID was deleted SUCCESSFULLY";
        }else {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CarAfterCreationDto addCar(CarCreateDto carCreateDto) {
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
        Car car = showCar(id);

        if (car != null){
            car.setYearOfRelease(Objects.equals(car.getYearOfRelease(), carCreateDto.getYearOfRelease()) ? car.getYearOfRelease() : carCreateDto.getYearOfRelease());
            car.setLicensePlate(Objects.equals(car.getLicensePlate(), carCreateDto.getLicensePlate()) ? car.getLicensePlate() : carCreateDto.getLicensePlate());
            car.setBrand(Objects.equals(car.getBrand(), carCreateDto.getBrand()) ? car.getBrand() : carCreateDto.getBrand());
            car.setStatus(CarStatus.AVAILABLE);
        }

        return carRepository.saveAndFlush(car);
    }
}
