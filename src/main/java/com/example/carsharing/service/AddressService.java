package com.example.carsharing.service;

import com.example.carsharing.entity.Address;

import java.util.UUID;

public interface AddressService {
    Address getAddressById(UUID id);
}
