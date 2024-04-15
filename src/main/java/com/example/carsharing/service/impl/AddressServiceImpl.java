package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Address;
import com.example.carsharing.exception.AddressNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.repository.AddressRepository;
import com.example.carsharing.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.ErrorManager;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address getAddressById(UUID id) {
        Address address = addressRepository.getAddressByAddressId(id);
        if (address == null){
            throw new AddressNotExistException(ErrorMessage.ADDRESS_NOT_EXIST);
        }
        return address;
    }
}
