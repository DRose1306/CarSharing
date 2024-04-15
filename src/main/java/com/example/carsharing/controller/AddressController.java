package com.example.carsharing.controller;

import com.example.carsharing.entity.Address;
import com.example.carsharing.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/get/{id}")
    public Address getAddressById(@PathVariable("id") UUID id) {
        return addressService.getAddressById(id);
    }
}
