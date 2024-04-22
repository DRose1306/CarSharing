package com.example.carsharing.controller;

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
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/update_user/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user){
        return userService.updateUserById(id, user);
    }
}

