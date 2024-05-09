package com.example.carsharing.dto;

import lombok.Data;

@Data
public class UserAfterRegistrationDto {
    String operation;
    String status;
    String login;
    String password;
    String createdAt;
}