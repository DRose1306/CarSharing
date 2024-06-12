package com.example.carsharing.security.security_util;

public class RolesPaths {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";

    public static final String[] AUTH_LIST = {
            "/cars/**",
            "/payments/**",
            "/trips/**",
            "/users/**"
    };

    public static final String[] SWAGGER_LIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/swagger-ui/",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
            "/configuration/**",
            "/configuration/ui",
            "/configuration/security",
            "/public",
            "/favicon.ico",
            "/h2-console/**"
    };
}
