package com.example.carsharing.service.impl;

import com.example.carsharing.entity.Authority;
import com.example.carsharing.exception.AuthorityNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
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
        Authority authority = authorityRepository.getAuthorityByAuthorityId(id);
        if (authority == null) {
            throw new AuthorityNotExistException(ErrorMessage.AUTHORITY_NOT_EXIST);
        }
        return authority;
    }
}