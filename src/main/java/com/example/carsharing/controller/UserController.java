package com.example.carsharing.controller;

import com.example.carsharing.dto.UserAfterCreationDto;
import com.example.carsharing.dto.UserCreateDto;
import com.example.carsharing.entity.User;
import com.example.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @DeleteMapping("/delete_user/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/create_user")
    public UserAfterCreationDto createUser(@RequestBody UserCreateDto userCreateDto){
        return userService.createUser(userCreateDto);
    }

    @PutMapping("/update_user/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user){
        return userService.updateUserById(id, user);
    }
}

