package com.example.carsharing.entity;

import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "trips")
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "trip_id")
    private UUID tripId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "distance")
    private double distance;

    @Column(name = "cost")
    private BigDecimal cost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Double.compare(distance, trip.distance) == 0 && Objects.equals(tripId, trip.tripId) && Objects.equals(user, trip.user) && Objects.equals(car, trip.car) && Objects.equals(startTime, trip.startTime) && Objects.equals(endTime, trip.endTime) && Objects.equals(cost, trip.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, user, car, startTime, endTime, distance, cost);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", user=" + user +
                ", car=" + car +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", distance=" + distance +
                ", cost=" + cost +
                '}';
    }
}
