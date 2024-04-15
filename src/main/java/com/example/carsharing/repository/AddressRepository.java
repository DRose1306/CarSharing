package com.example.carsharing.repository;

import com.example.carsharing.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    Address getAddressByAddressId(UUID id);
}
