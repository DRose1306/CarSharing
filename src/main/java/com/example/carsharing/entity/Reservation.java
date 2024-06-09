package com.example.carsharing.entity;

import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a reservation in the system.
 * It stores detailed information about the reservation
 * including the start time, end time, associated car, and user.
 * <p>
 * This class provides a representation of a reservation
 * with various attributes like start time, end time, car, user, etc.
 * <p>
 * This is used throughout the application to manage and persist reservation data.
 *
 * @author Urmat Bolotbek Uulu
 */
@Entity
@Getter
@Setter
@Table(name = "reservations")
@NoArgsConstructor
@ToString
@Schema(description = "Entity representing a reservation in the carsharing system.")
public class Reservation {

    /**
     * Unique identifier of the reservation.
     * Generated using a custom UUID generator.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
    @Column(name = "reservation_id")
    @Schema(description = "Unique identifier of the reservation.")
    private UUID reservationId;

    /**
     * Start time of the reservation.
     */
    @Column(name = "start_time")
    @Schema(description = "Start time of the reservation.")
    private LocalDateTime startTime;

    /**
     * End time of the reservation.
     */
    @Column(name = "end_time")
    @Schema(description = "End time of the reservation.")
    private LocalDateTime endTime;

    /**
     * Car associated with this reservation.
     * This relationship is bidirectional and managed by the car entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    @Schema(description = "Car associated with this reservation.")
    private Car car;

    /**
     * User associated with this reservation.
     * This relationship is bidirectional and managed by the user entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Schema(description = "User associated with this reservation.")
    private User user;

    /**
     * Equals method for comparing Reservation objects.
     * It compares based on the reservation ID and other reservation fields.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(reservationId, that.reservationId) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    /**
     * Generates the hash code for the Reservation object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(reservationId, startTime, endTime);
    }
}
