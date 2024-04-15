package com.example.carsharing.controller;

import com.example.carsharing.entity.Role;
import com.example.carsharing.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/get/{id}")
    public Role getRoleById(@PathVariable("id") UUID id) {
        return roleService.getRoleById(id);
    }
}

