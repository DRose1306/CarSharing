package com.example.carsharing.service.interfaces;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
    String deleteUserById(UUID id);
    UserAfterRegistrationDto createUser(UserRegistrationDto userRegistrationDto);
    User updateUserById(UUID id, User user);
    List<User> getAllUsers();
}
