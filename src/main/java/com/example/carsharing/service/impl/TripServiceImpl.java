package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Trip;
import com.example.carsharing.exception.TripNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.repository.TripRepository;
import com.example.carsharing.service.TripService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    @Override
    public Trip getTripById(UUID id) {
        Trip trip = tripRepository.getTripByTripId(id);
        if (trip == null) {
            throw new TripNotExistException(ErrorMessage.TRIP_NOT_EXIST);
        }
        return trip;
    }

    @Override
    @Transactional
    public void deleteTripById(UUID id) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip == null) {
            throw new TripNotExistException(ErrorMessage.TRIP_NOT_EXIST);
        }
        tripRepository.deleteTripByTripId(id);
    }

    @Override
    public Trip createTrip(Trip trip) {
        return tripRepository.saveAndFlush(trip);
    }

    @Override
    @Transactional
    public Trip updateTripById(UUID id, Trip updatedTrip) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip == null) {
            throw new TripNotExistException(ErrorMessage.TRIP_NOT_EXIST);
        }
        trip.setStartTime(updatedTrip.getStartTime());
        trip.setEndTime(updatedTrip.getEndTime());
        trip.setDistance(updatedTrip.getDistance());
        trip.setCost(updatedTrip.getCost());
        trip.setUser(updatedTrip.getUser());
        trip.setCar(updatedTrip.getCar());

        return tripRepository.saveAndFlush(trip);    }
}
