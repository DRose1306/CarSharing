package com.example.carsharing.dto;

import lombok.Data;

/**
 * DTO representing user registration details after registration.
 */
@Data
public class UserAfterRegistrationDto {

    /**
     * The operation performed on the user (e.g., registration).
     */
    String operation;

    /**
     * The status of the user after the operation.
     */
    String status;

    /**
     * The login username of the user.
     */
    String login;

    /**
     * The password of the user.
     */
    String password;

    /**
     * The date and time when the user was created.
     */
    String createdAt;
}
