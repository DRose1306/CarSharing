package com.example.carsharing.controller;

import com.example.carsharing.entity.Authority;
import com.example.carsharing.service.AuthorityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/authority")
public class AuthorityController {
    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping("/get/{id}")
    public Authority getAuthorityById(@PathVariable("id") UUID id) {
        return authorityService.getAuthorityById(id);
    }

}
