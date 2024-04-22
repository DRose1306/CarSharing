package com.example.carsharing.service;

import com.example.carsharing.entity.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
    void deleteUserById(UUID id);
    User createUser(User user);
    User updateUserById(UUID id, User updatedUser);
}
