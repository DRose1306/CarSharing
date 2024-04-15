package com.example.carsharing.service;

import com.example.carsharing.entity.Role;

import java.util.UUID;

public interface RoleService {

    Role getRoleById(UUID id);
}
