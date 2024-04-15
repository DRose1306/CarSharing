package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Trip;
import com.example.carsharing.repository.TripRepository;
import com.example.carsharing.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    @Override
    public Trip getTripById(UUID id) {
        return tripRepository.getTripByTripId(id);
    }
}
