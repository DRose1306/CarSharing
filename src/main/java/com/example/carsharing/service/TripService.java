package com.example.carsharing.service;

import com.example.carsharing.entity.Trip;

import java.util.UUID;

public interface TripService {
    Trip getTripById(UUID id);
}
