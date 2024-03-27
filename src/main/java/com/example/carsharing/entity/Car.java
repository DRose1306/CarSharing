package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.CarStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cars")
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "year_of_release")
    private String yearOfRelease;

    @Column(name = "licence_plate")
    private String licensePlate;

    @Column(name = "current_location")
    private Point current_location;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CarStatus status;

    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "model")
    private Model model;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(yearOfRelease, car.yearOfRelease) && Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, yearOfRelease, licensePlate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", current_location=" + current_location +
                ", status=" + status +
                ", brand=" + brand +
                ", model=" + model +
                '}';
    }
}
