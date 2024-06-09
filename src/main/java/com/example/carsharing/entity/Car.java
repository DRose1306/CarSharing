package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.CarBrand;
import com.example.carsharing.entity.enums.CarStatus;
import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a car in the system.
 * It stores detailed information about the car
 * which can be linked to various operations and statuses.
 * <p>
 * This class provides a representation of a car
 * with various attributes like brand, status, year of release, etc.
 * <p>
 * This is used throughout the application to manage and persist car data.
 *
 * @author Urmat Bolotbek Uulu
 */
@Entity
@Getter
@Setter
@Table(name = "cars")
@NoArgsConstructor
@Schema(description = "Entity representing a car in the carsharing system.")
public class Car {

    /**
     * Unique identifier of the car.
     * Generated using a custom UUID generator.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
    @Column(name = "car_id")
    @Schema(description = "Unique identifier of the car.")
    private UUID carId;

    /**
     * Year of release of the car.
     */
    @Column(name = "year_of_release")
    @Schema(description = "Year of release of the car.")
    private String yearOfRelease;

    /**
     * License plate of the car.
     */
    @Column(name = "license_plate")
    @Schema(description = "License plate of the car.")
    private String licensePlate;

    /**
     * Status of the car.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "car_status")
    @Schema(description = "Status of the car.")
    private CarStatus status;

    /**
     * Brand of the car.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "car_brand")
    @Schema(description = "Brand of the car.")
    private CarBrand brand;

    /**
     * Timestamp when the car was added.
     */
    @Column(name = "created_at")
    @Schema(description = "Timestamp when the car was added.")
    private Timestamp createdAt;

    /**
     * Equals method for comparing Car objects.
     * It compares based on the car ID and other car fields.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) &&
                Objects.equals(yearOfRelease, car.yearOfRelease) &&
                Objects.equals(licensePlate, car.licensePlate) &&
                status == car.status &&
                brand == car.brand &&
                Objects.equals(createdAt, car.createdAt);
    }

    /**
     * Generates the hash code for the Car object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(carId, yearOfRelease, licensePlate, status, brand, createdAt);
    }

    /**
     * Returns the string representation of the Car object.
     *
     * @return the string representation of the Car
     */
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
