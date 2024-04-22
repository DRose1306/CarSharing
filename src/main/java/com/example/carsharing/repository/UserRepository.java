package com.example.carsharing.repository;

import com.example.carsharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User getUserByUserId(UUID id);
    void deleteUserByUserId(UUID id);
}
