package com.example.carsharing.service.impl;

import com.example.carsharing.entity.UserInfo;
import com.example.carsharing.exception.UserInfoNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
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
        UserInfo userInfo = userInfoRepository.getUserInfoByUserInfoId(id);
        if (userInfo == null){
            throw new UserInfoNotExistException(ErrorMessage.USERINFO_NOT_EXIST);
        }
        return userInfo;
    }
}
