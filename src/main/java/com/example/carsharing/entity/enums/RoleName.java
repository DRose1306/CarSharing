package com.example.carsharing.entity.enums;

import lombok.*;


/**
 * Enum representing different roles in the system.
 * Each role corresponds to a specific level of access or authority.
 */
@Getter
@AllArgsConstructor
public enum RoleName {
    /**
     * Administrator role.
     */
    ROLE_ADMIN,
    /**
     * Manager role.
     */
    ROLE_MANAGER,
    /**
     * User role.
     */
    ROLE_USER,
    /**
     * Guest role.
     */
    ROLE_GUEST
}
