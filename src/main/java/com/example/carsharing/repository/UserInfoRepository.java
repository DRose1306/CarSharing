package com.example.carsharing.repository;

import com.example.carsharing.entity.UserInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    Optional<UserInfo> findByLogin(String login);
}
