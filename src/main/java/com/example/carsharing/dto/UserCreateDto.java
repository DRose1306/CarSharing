package com.example.carsharing.dto;

import com.example.carsharing.entity.UserInfo;
import lombok.Value;

import java.io.Serializable;

@Value
public class UserCreateDto implements Serializable {
    String firstName;
    String lastName;
    UserInfo userInfo;
}