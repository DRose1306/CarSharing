package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.CarBrand;
import com.example.carsharing.entity.enums.CarStatus;
import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.sql.Timestamp;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "car_status")
    private CarStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_brand")
    private CarBrand brand;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(yearOfRelease, car.yearOfRelease) && Objects.equals(licensePlate, car.licensePlate) && status == car.status && brand == car.brand && Objects.equals(createdAt, car.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, yearOfRelease, licensePlate, status, brand, createdAt);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", status=" + status +
                ", brand=" + brand +
                ", createdAt=" + createdAt +
                '}';
    }
}
