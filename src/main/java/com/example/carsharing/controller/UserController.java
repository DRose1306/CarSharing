package com.example.carsharing.controller;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;
import com.example.carsharing.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/get_all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUserById(@PathVariable UUID id) {
        return userService.deleteUserById(id);
    }

    @PostMapping("/registration/create")
    public UserAfterRegistrationDto createUser(@RequestBody UserRegistrationDto userRegistrationDto){
        return userService.createUser(userRegistrationDto);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user){
        return userService.updateUserById(id, user);
    }
}

