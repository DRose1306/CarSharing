package com.example.carsharing.entity;

import com.example.carsharing.deleteThis.Brand;
import com.example.carsharing.deleteThis.Model;
import com.example.carsharing.entity.enums.CarBrand;
import com.example.carsharing.entity.enums.CarStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
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
    @Column(name = "car_id")
    private UUID carId;

    @Column(name = "year_of_release")
    private String yearOfRelease;

    @Column(name = "licence_plate")
    private String licensePlate;

    @Column(name = "current_location")
    private Point currentLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CarStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private CarBrand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    // private Model model; //TODO разобраться как


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(yearOfRelease, car.yearOfRelease) && Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, yearOfRelease, licensePlate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + carId +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", current_location=" + currentLocation +
                ", status=" + status +
                ", brand=" + brand +
                //        ", model=" + model +
                '}';
    }
}
