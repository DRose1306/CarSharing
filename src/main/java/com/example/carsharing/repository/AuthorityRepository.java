package com.example.carsharing.repository;

import com.example.carsharing.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
    Authority getAuthorityByAuthorityId(UUID id);
}
