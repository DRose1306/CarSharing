package com.example.carsharing.security.security_util;

public class RolesPaths {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";

    public static final String[] ADMIN_LIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/swagger-ui.html",
            "/webjars/**",
            "/configuration/**",
            "/public",
            "/favicon.ico",
            "/swagger-ui/**",
            "/users/**",
            "/payments/**",
            "/trips/**",
            "/cars/**"
    };

    public static final String[] USER_LIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/webjars/**",
            "/favicon.ico",
            "/swagger-ui/**",
            "/configuration/**",
            "/h2-console/**",
            "/cars/show_car/{id}",
            "/payments/get/{id}**",
            "/trips/get/{id}",
            "/users/get/{id}",
            "/users/delete/{id}",
            "/users/update/{id}",
            "/users/registration/create"
    };

    public static final String[] MANAGER_LIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/swagger-ui.html",
            "/webjars/**",
            "/configuration/**",
            "/favicon.ico",
            "/swagger-ui/**",
            "/users/**",
            "/payments/**",
            "/trips/**",
            "/cars/**"
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
