package com.example.carsharing.dto;

import lombok.Value;
import java.io.Serializable;

@Value
public class UserRegistrationDto implements Serializable {
    String firstName;
    String lastName;
    String dateOfBirth;
    String email;
}