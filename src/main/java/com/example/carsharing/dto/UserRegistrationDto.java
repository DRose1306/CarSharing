package com.example.carsharing.dto;

import com.example.carsharing.entity.Address;
import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Value
public class UserRegistrationDto implements Serializable {
    String firstName;
    String lastName;
    String dateOfBirth;
    String email;
}