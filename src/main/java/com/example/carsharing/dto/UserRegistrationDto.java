package com.example.carsharing.dto;

import com.example.carsharing.entity.enums.DriverLicense;
import com.example.carsharing.validation.annotation.EmailChecker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO representing user registration details.
 */
@Data
public class UserRegistrationDto {

    /**
     * The first name of the user.
     */
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 30, message = "Name should be between 2 and 30 characters")
    String firstName;

    /**
     * The last name of the user.
     */
    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 1, max = 30, message = "Lastname should be between 2 and 30 characters")
    String lastName;

    /**
     * The date of birth of the user.
     */
    String dateOfBirth;

    /**
     * The email address of the user.
     */
    @EmailChecker
    String email;

    /**
     * Password of the user.
     */
    String password;

    /**
     * The driver's license type of the user.
     */
    DriverLicense driverLicense;

    /**
     * The identifier of the driver's license of the user.
     */
    String driverLicenseIdentifier;
}
