package com.example.carsharing.service.impl;

import com.example.carsharing.dto.TripAfterCreationDto;
import com.example.carsharing.dto.TripCreateDto;
import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.Trip;
import com.example.carsharing.entity.User;
import com.example.carsharing.exception.CarNotExistException;
import com.example.carsharing.exception.TripAlreadyExistException;
import com.example.carsharing.exception.TripNotExistException;
import com.example.carsharing.exception.UserNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.mapper.TripMapper;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.repository.TripRepository;
import com.example.carsharing.repository.UserRepository;
import com.example.carsharing.service.interfaces.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    /**
     * Retrieves a trip by its identifier.
     *
     * @param id Trip identifier.
     * @return Trip object.
     * @throws TripNotExistException if the trip does not exist.
     */
    @Override
    @Transactional
    public Trip getTripById(UUID id) {
        Trip trip = tripRepository.getTripByTripId(id);
        if (trip == null) {
            throw new TripNotExistException(ErrorMessage.TRIP_NOT_EXIST);
        }
        return trip;
    }

    /**
     * Deletes a trip by its identifier.
     *
     * @param id Trip identifier.
     * @return A message about the successful deletion of the trip.
     * @throws TripNotExistException if the trip does not exist.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String deleteTripById(UUID id) {
        Trip trip = tripRepository.getTripByTripId(id);
        if (trip != null) {
            tripRepository.deleteById(id);
            return "Trip with this ID was deleted SUCCESSFULLY";
        } else {
            throw new TripNotExistException(ErrorMessage.TRIP_NOT_EXIST);
        }
    }


    /**
     * Creates a new trip.
     *
     * @param tripCreateDto Data about the trip to be created.
     * @return Data about the created trip.
     * @throws TripAlreadyExistException if the trip already exists.
     * @throws UserNotExistException     if the user does not exist.
     * @throws CarNotExistException      if the car does not exist.
     */
    @Override
    @Transactional
    public TripAfterCreationDto createTrip(TripCreateDto tripCreateDto) {
        Trip trip = tripRepository.findByStartTimeAndUser_UserId(LocalDateTime.parse(tripCreateDto.getStartTime()), UUID.fromString(tripCreateDto.getUserId()));
        if (trip != null) {
            throw new TripAlreadyExistException(ErrorMessage.TRIP_ALREADY_EXIST);
        }

        User user = userRepository.findById(UUID.fromString(tripCreateDto.getUserId())).orElse(null);
        if (user == null) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }

        Car car = carRepository.findById(UUID.fromString(tripCreateDto.getCarId())).orElse(null);
        if (car == null) {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }

        Trip entity = tripMapper.toEntity(tripCreateDto);

        entity.setUser(user);
        entity.setCar(car);

        Trip tripAfterCreation = tripRepository.save(entity);
        return tripMapper.toDto(tripAfterCreation);
    }

    /**
     * Updates trip information by its identifier.
     *
     * @param id            Trip identifier.
     * @param tripCreateDto Updated trip data.
     * @return Updated trip object.
     * @throws TripNotExistException if the trip does not exist.
     */
    @Override
    @Transactional
    public Trip updateTripById(UUID id, TripCreateDto tripCreateDto) {
        Trip trip = getTripById(id);

        if (trip != null) {
            trip.setStartTime(!Objects.equals(trip.getStartTime(), tripCreateDto.getStartTime()) ? trip.getStartTime() : LocalDateTime.parse(tripCreateDto.getStartTime()));
            trip.setEndTime(!Objects.equals(trip.getEndTime(), tripCreateDto.getEndTime()) ? trip.getEndTime() : LocalDateTime.parse(tripCreateDto.getEndTime()));
        }

        return tripRepository.saveAndFlush(trip);
    }
}
