package com.example.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAfterRegistrationDto implements Serializable {
    private String operation;
    private String status;
    private String login;
    private String password;
    private String createdAt;
}