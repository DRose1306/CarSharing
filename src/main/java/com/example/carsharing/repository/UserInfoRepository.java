package com.example.carsharing.repository;

import com.example.carsharing.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {

    Optional<UserInfo> findByEmail(String email);
    boolean existsByEmail(String email);
}
