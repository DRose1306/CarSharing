package com.example.carsharing.repository;

import com.example.carsharing.entity.Role;
import com.example.carsharing.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByRoleName(RoleName rolesName);
}