package com.example.carsharing.entity.enums;

import lombok.*;

/**
 * Enum representing different statuses of cars.
 * Each status corresponds to a specific condition of a car in the system.
 */
@Getter
@AllArgsConstructor
public enum CarStatus {
    /**
     * Car is available for reservation.
     */
    AVAILABLE,
    /**
     * Car is reserved by a user.
     */
    RESERVED,
    /**
     * Car is currently in use by a user.
     */
    IN_USE,
    /**
     * Car is faulty and cannot be used.
     */
    FAULTY,
    /**
     * Car is under repair.
     */
    UNDER_REPAIR,
    /**
     * Car has been returned by a user.
     */
    RETURNED,
    /**
     * Car is blocked and cannot be used.
     */
    BLOCKED,
    /**
     * Car is under maintenance.
     */
    UNDER_MAINTENANCE
}
