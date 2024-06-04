package com.example.carsharing.security.security_util;

public class RolesPaths {
    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";

    public static final String[] ADMIN_LIST =

            {
                    "swagger-ui/**",
                    "/car/**",
                    "/payment/**",
                    "/trip/**",
                    "/user/**"
            };
    public static String[] MANAGER_LIST =

            {
                    "/payment/**",
                    "/trip/**",
                    "/user/**"
            };
}
