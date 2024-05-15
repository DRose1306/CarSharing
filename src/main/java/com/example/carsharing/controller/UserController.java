package com.example.carsharing.controller;

import com.example.carsharing.annotation.*;
import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;
import com.example.carsharing.service.interfaces.UserService;
import com.example.carsharing.validation.annotation.UuidFormatChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetUserById(path = "/get/{id}")
    public User getUserById(@PathVariable("id") @UuidFormatChecker String id) {
        return userService.getUserById(UUID.fromString(id));
    }

    @GetAllUsers(path = "/get_all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteUser(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUserById(@PathVariable("id") @UuidFormatChecker String id) {
        return userService.deleteUserById(UUID.fromString(id));
    }

    @CreateUser(path = "/registration/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAfterRegistrationDto createUser(@RequestBody UserRegistrationDto userRegistrationDto){
        return userService.createUser(userRegistrationDto);
    }

    @UpdateUser(path = "/update/{id}")
    public User updateUser(@PathVariable("id") @UuidFormatChecker String id, @RequestBody @Valid User user){
        return userService.updateUserById(UUID.fromString(id), user);
    }
}

