package com.example.carsharing.service.impl;

import com.example.carsharing.entity.UserInfo;
import com.example.carsharing.repository.UserInfoRepository;
import com.example.carsharing.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Override
    public UserInfo getUserInfoById(UUID id) {
        return userInfoRepository.getUserInfoByUserInfoId(id);
    }
}
