package com.example.carsharing.controller;

import com.example.carsharing.annotation.*;
import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;
import com.example.carsharing.service.interfaces.UserService;
import com.example.carsharing.validation.annotation.UuidFormatChecker;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class responsible for managing user-related HTTP requests.
 */
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Retrieves user information by its ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user object.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    @GetUserById(path = "/get/{id}")
    public User getUserById(@PathVariable("id") @UuidFormatChecker String id) {
        return userService.getUserById(UUID.fromString(id));
    }

    /**
     * Retrieves information about all users.
     *
     * @return A list of user objects.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetAllUsers(path = "/get_all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id The ID of the user to delete.
     * @return A message indicating the success of the operation.
     */
    @PreAuthorize("hasRole('USER')")
    @DeleteUser(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUserById(@PathVariable("id") @UuidFormatChecker String id) {
        return userService.deleteUserById(UUID.fromString(id));
    }

    /**
     * Creates a new user.
     *
     * @param userRegistrationDto The DTO containing the information for creating the new user.
     * @return The DTO containing information about the newly created user.
     */
    @PermitAll
    @CreateUser(path = "/registration/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAfterRegistrationDto createUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        return userService.createUser(userRegistrationDto);
    }

    /**
     * Updates user information.
     *
     * @param id   The ID of the user to update.
     * @param user The updated user object.
     * @return The updated user object.
     */
    @PreAuthorize("hasRole('USER')")
    @UpdateUser(path = "/update/{id}")
    public User updateUser(@PathVariable("id") @UuidFormatChecker String id, @RequestBody @Valid User user) {
        return userService.updateUserById(UUID.fromString(id), user);
    }
}
