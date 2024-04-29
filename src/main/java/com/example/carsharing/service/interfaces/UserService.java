package com.example.carsharing.service.interfaces;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
    void deleteUserById(UUID id);
    UserAfterRegistrationDto createUser(UserRegistrationDto userRegistrationDto);
    User updateUserById(UUID id, UserRegistrationDto userRegistrationDto);
}
