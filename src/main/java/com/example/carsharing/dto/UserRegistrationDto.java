package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.DriverLicense;
import com.example.carsharing.validation.annotation.EmailChecker;
import lombok.Data;

@Data
public class UserRegistrationDto {
    String firstName;
    String lastName;
    String dateOfBirth;
    @EmailChecker
    String email;
    DriverLicense driverLicense;
    String driverLicenseIdentifier;
}