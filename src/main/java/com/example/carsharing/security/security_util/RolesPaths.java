package com.example.carsharing.security.security_util;

public class RolesPaths {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";
    public static final String GUEST = "GUEST";

    public static final String[] USER_LIST = {
            "/configuration/**",
            "/h2-console/**",
            "/cars/**",
//            "/cars/show_car/{id}",
            "/payments/get/{id}**",
            "/trips/get/{id}",
            "/users/get/{id}",
            "/users/delete/{id}",
            "/users/update/{id}",
            "/users/registration/create"
    };

    public static final String[] ADMIN_LIST = {
            "/configuration/**",
            "/h2-console/**",
            "/cars/**",
            "/payments/**",
            "/trips/**",
            "/users/get/{id}",
            "/users/get_all",
            "/users/registration/create"
    };

    public static final String[] MANAGER_LIST = {
            "/configuration/**",
            "/h2-console/**",
            "/cars/**",
            "/payments/**",
            "/trips/**",
            "/users/get/{id}",
            "/users/get_all",
            "/users/registration/create"
    };

    public static final String[] GUEST_LIST = {
            "/users/registration/create"
    };
}
