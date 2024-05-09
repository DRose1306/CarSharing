package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.DriverLicense;
import lombok.Value;

@Value
public class UserRegistrationDto {
    String firstName;
    String lastName;
    String dateOfBirth;
    String email;
    DriverLicense driverLicense;
    String driverLicenseIdentifier;
}