package com.example.carsharing.entity;

import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a trip in the system.
 * It stores detailed information about the trip
 * including the start time, end time, distance, cost, associated user, and car.
 * <p>
 * This class provides a representation of a trip
 * with various attributes like start time, end time, distance, cost, user, car, etc.
 * <p>
 * This is used throughout the application to manage and persist trip data.
 *
 * @author Urmat Bolotbek Uulu
 */
@Entity
@Getter
@Setter
@Table(name = "trips")
@NoArgsConstructor
@ToString
@Schema(description = "Entity representing a trip in the carsharing system.")
public class Trip {

    /**
     * Unique identifier of the trip.
     * Generated using a custom UUID generator.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
    @Column(name = "trip_id")
    @Schema(description = "Unique identifier of the trip.")
    private UUID tripId;

    /**
     * Start time of the trip.
     */
    @Column(name = "start_time")
    @Schema(description = "Start time of the trip.")
    private LocalDateTime startTime;

    /**
     * End time of the trip.
     */
    @Column(name = "end_time")
    @Schema(description = "End time of the trip.")
    private LocalDateTime endTime;

    /**
     * Distance traveled during the trip.
     */
    @Column(name = "distance")
    @Schema(description = "Distance traveled during the trip.")
    private double distance;

    /**
     * Cost of the trip.
     */
    @Column(name = "cost")
    @Schema(description = "Cost of the trip.", example = "75.00")
    private BigDecimal cost;

    /**
     * User associated with this trip.
     * This relationship is bidirectional and managed by the user entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Schema(description = "User associated with this trip.")
    private User user;

    /**
     * Car associated with this trip.
     * This relationship is bidirectional and managed by the car entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    @Schema(description = "Car associated with this trip.")
    private Car car;

    /**
     * Equals method for comparing Trip objects.
     * It compares based on the trip ID and other trip fields.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Double.compare(trip.distance, distance) == 0 &&
                Objects.equals(tripId, trip.tripId) &&
                Objects.equals(startTime, trip.startTime) &&
                Objects.equals(endTime, trip.endTime) &&
                Objects.equals(cost, trip.cost);
    }

    /**
     * Generates the hash code for the Trip object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(tripId, startTime, endTime, distance, cost);
    }
}
