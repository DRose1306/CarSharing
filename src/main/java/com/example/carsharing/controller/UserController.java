package com.example.carsharing.controller;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;
import com.example.carsharing.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }

    //TODO не работает
    @DeleteMapping("/delete_user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/create_user")
    public UserAfterRegistrationDto createUser(@RequestBody UserRegistrationDto userRegistrationDto){
        return userService.createUser(userRegistrationDto);
    }

    @PutMapping("/update_user/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody UserRegistrationDto userRegistrationDto){
        return userService.updateUserById(id, userRegistrationDto);
    }
}

