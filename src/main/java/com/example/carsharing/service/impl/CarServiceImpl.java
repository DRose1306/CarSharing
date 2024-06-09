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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    @Transactional
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
        if (car != null) {
            carRepository.deleteById(id);
            return "Car with this ID was deleted SUCCESSFULLY";
        } else {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public Car updateCarById(UUID id, Car updatingCar) {
        Car car = showCar(id);
        if (car == null) {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }

        Set<String> nullProperties = getNullPropertyNames(updatingCar);
        BeanUtils.copyProperties(updatingCar, car, nullProperties.toArray(new String[0]));

        return carRepository.saveAndFlush(car);
    }

    private Set<String> getNullPropertyNames(Object object) {
        final BeanWrapper src = new BeanWrapperImpl(object);
        PropertyDescriptor[] descriptors = src.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        Arrays.stream(descriptors).forEach(descriptor -> {
            Object propertyValue = src.getPropertyValue(descriptor.getName());
            if (propertyValue == null) {
                nullProperties.add(descriptor.getName());
            }
        });
        return nullProperties;
    }
}
