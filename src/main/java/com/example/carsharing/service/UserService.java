package com.example.carsharing.service;

import com.example.carsharing.entity.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);
}
