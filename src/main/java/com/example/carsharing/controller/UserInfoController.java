package com.example.carsharing.controller;

import com.example.carsharing.entity.UserInfo;
import com.example.carsharing.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/get/{id}")
    public UserInfo getUserInfoById(@PathVariable("id") UUID id) {
        return userInfoService.getUserInfoById(id);
    }
}
