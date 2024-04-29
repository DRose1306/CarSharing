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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Trip getTripById(UUID id) {
        Trip trip = tripRepository.getTripByTripId(id);
        if (trip == null) {
            throw new TripNotExistException(ErrorMessage.TRIP_NOT_EXIST);
        }
        return trip;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteTripById(UUID id) {
        tripRepository.findById(id).orElseThrow(() -> new TripNotExistException(ErrorMessage.TRIP_NOT_EXIST));
        tripRepository.deleteTripByTripId(id);
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public TripAfterCreationDto createTrip(TripCreateDto tripCreateDto) {
        Trip trip = tripRepository.findByStartTimeAndUser_UserId(LocalDateTime.parse(tripCreateDto.getStartTime()), UUID.fromString(tripCreateDto.getUserId()));
        if (trip != null){
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
        Trip tripAfterCreation = tripRepository.save(entity);
        entity.setUser(user);
        entity.setCar(car);
        return tripMapper.toDto(tripAfterCreation);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Trip updateTripById(UUID id, TripCreateDto tripCreateDto) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new TripNotExistException(ErrorMessage.TRIP_NOT_EXIST));

        User user = userRepository.findById(UUID.fromString(tripCreateDto.getUserId())).orElse(null);
        if (user == null) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }

        Car car = carRepository.findById(UUID.fromString(tripCreateDto.getCarId())).orElse(null);
        if (car == null) {
            throw new CarNotExistException(ErrorMessage.CAR_NOT_EXIST);
        }

        trip.setStartTime(LocalDateTime.parse(tripCreateDto.getStartTime()));
        trip.setEndTime(LocalDateTime.parse(tripCreateDto.getEndTime()));
        trip.setUser(user);
        trip.setCar(car);

        return tripRepository.saveAndFlush(trip);    }
}
