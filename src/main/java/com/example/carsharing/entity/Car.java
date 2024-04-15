package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.CarBrand;
import com.example.carsharing.entity.enums.CarStatus;
import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "car_id")
    private UUID carId;

    @Column(name = "year_of_release")
    private String yearOfRelease;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "current_location")
    private Point currentLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_status")
    private CarStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_brand")
    private CarBrand brand;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(yearOfRelease, car.yearOfRelease) && Objects.equals(licensePlate, car.licensePlate) && brand == car.brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, yearOfRelease, licensePlate, brand);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", currentLocation=" + currentLocation +
                ", status=" + status +
                ", brand=" + brand +
                '}';
    }
}
