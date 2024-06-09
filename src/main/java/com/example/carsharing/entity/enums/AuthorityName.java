package com.example.carsharing.entity.enums;

import lombok.*;


/**
 * Enum representing different authority names.
 * Each authority name corresponds to a specific permission in the system.
 */
@Getter
@AllArgsConstructor
public enum AuthorityName {
    /**
     * Authority to create entities.
     */
    CREATE,
    /**
     * Authority to read entities.
     */
    READ,
    /**
     * Authority to update entities.
     */
    UPDATE,
    /**
     * Authority to delete entities.
     */
    DELETE
}
