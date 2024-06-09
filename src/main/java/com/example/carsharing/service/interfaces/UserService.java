package com.example.carsharing.service.interfaces;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    /**
     * Retrieves a user by their identifier.
     *
     * @param id User identifier.
     * @return User object.
     */
    User getUserById(UUID id);

    /**
     * Deletes a user by their identifier.
     *
     * @param id User identifier.
     * @return A message about the successful deletion of the user.
     */
    String deleteUserById(UUID id);

    /**
     * Creates a new user.
     *
     * @param userRegistrationDto Data about the user to be created.
     * @return Data about the created user.
     */
    UserAfterRegistrationDto createUser(UserRegistrationDto userRegistrationDto);

    /**
     * Updates user information by their identifier.
     *
     * @param id   User identifier.
     * @param user Updated user data.
     * @return Updated user object.
     */
    User updateUserById(UUID id, User user);

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    List<User> getAllUsers();
}
