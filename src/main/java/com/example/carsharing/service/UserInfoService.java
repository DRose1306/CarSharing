package com.example.carsharing.service;

import com.example.carsharing.entity.UserInfo;

import java.util.UUID;

public interface UserInfoService {

    UserInfo getUserInfoById(UUID id);
}
