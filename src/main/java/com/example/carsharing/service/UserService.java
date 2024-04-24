package com.example.carsharing.service;

import com.example.carsharing.dto.UserAfterCreationDto;
import com.example.carsharing.dto.UserCreateDto;
import com.example.carsharing.entity.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
    void deleteUserById(UUID id);
    UserAfterCreationDto createUser(UserCreateDto userCreateDto);
    User updateUserById(UUID id, User updatedUser);
}
