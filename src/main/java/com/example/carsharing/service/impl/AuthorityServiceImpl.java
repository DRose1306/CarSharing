package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Authority;
import com.example.carsharing.repository.AuthorityRepository;
import com.example.carsharing.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    @Override
    public Authority getAuthorityById(UUID id) {
        return authorityRepository.getAuthorityByAuthorityId(id);
    }
}
