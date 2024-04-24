package com.example.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAfterCreationDto implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
}