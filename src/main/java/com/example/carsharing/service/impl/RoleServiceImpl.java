package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Role;
import com.example.carsharing.exception.RoleNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.repository.RoleRepository;
import com.example.carsharing.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleById(UUID id) {
        Role role = roleRepository.getRoleByRoleId(id);
        if (role == null) {
            throw new RoleNotExistException(ErrorMessage.ROLE_NOT_EXIST);
        }
        return role;
    }
}
