package com.example.carsharing.entity.enums;

import lombok.*;


/**
 * Enum representing different types of driver's licenses.
 * Each type corresponds to a specific category of driver's license.
 */
@Getter
@AllArgsConstructor
public enum DriverLicense {
    /**
     * Type A driver's license.
     */
    A,
    /**
     * Type B driver's license.
     */
    B,
    /**
     * Type C driver's license.
     */
    C,
    /**
     * Type D driver's license.
     */
    D,
    /**
     * Type E driver's license.
     */
    E
}
