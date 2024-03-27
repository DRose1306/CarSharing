package com.example.carsharing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "distance")
    private double distance;

    @Column(name = "cost")
    private BigDecimal cost;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne //TODO уточнить
    @JoinColumn(name = "user_id")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Double.compare(distance, trip.distance) == 0 && Objects.equals(id, trip.id) && Objects.equals(startTime, trip.startTime) && Objects.equals(endTime, trip.endTime) && Objects.equals(cost, trip.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, distance, cost);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", distance=" + distance +
                ", cost=" + cost +
                ", car=" + car +
                ", user=" + user +
                '}';
    }
}
