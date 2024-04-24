package com.example.carsharing.repository;

import com.example.carsharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByFirstNameAndLastName(String firstName, String lastName);
    User getUserByUserId(UUID id);
    void deleteUserByUserId(UUID id);
}
