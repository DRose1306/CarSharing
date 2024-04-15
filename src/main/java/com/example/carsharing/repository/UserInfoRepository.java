package com.example.carsharing.repository;

import com.example.carsharing.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    UserInfo getUserInfoByUserInfoId(UUID id);
}
