package com.example.carsharing.service;

import com.example.carsharing.entity.Authority;

import java.util.UUID;

public interface AuthorityService {
    Authority getAuthorityById(UUID id);
}
