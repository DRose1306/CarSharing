package com.example.carsharing.service.interfaces;

import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;

import java.util.UUID;

public interface CarService {
    /**
     * Retrieves a car by its identifier.
     *
     * @param id Car identifier.
     * @return Car object.
     */
    Car showCar(UUID id);

    /**
     * Deletes a car by its identifier.
     *
     * @param id Car identifier.
     * @return A message about the successful deletion of the car.
     */
    String deleteCarById(UUID id);

    /**
     * Adds a new car.
     *
     * @param carCreateDto Data about the car to be added.
     * @return Data about the created car.
     */
    CarAfterCreationDto addCar(CarCreateDto carCreateDto);

    /**
     * Updates car information by its identifier.
     *
     * @param id  Car identifier.
     * @param car Updated car data.
     * @return Updated car object.
     */
    Car updateCarById(UUID id, Car car);
}
