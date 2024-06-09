package com.example.carsharing.entity;

import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * This class represents a user in the system.
 * It stores detailed information about the user
 * including first name, last name, and creation timestamp.
 * <p>
 * This class provides a representation of a user
 * with various attributes like first name, last name, etc.
 * <p>
 * This is used throughout the application to manage and persist user data.
 *
 * @author Urmat Bolotbek Uulu
 */
@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@ToString
@Schema(description = "Entity representing a user in the carsharing system.")
public class User {

    /**
     * Unique identifier of the user.
     * Generated using a custom UUID generator.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "user_id")
    @Schema(description = "Unique identifier of the user.")
    private UUID userId;

    /**
     * First name of the user.
     */
    @Column(name = "first_name")
    @Schema(description = "First name of the user.")
    private String firstName;

    /**
     * Last name of the user.
     */
    @Column(name = "last_name")
    @Schema(description = "Last name of the user.")
    private String lastName;

    /**
     * Creation timestamp of the user.
     * This is automatically set when the user is created.
     */
    @JsonIgnore
    @Column(name = "created_at")
    @Schema(description = "Creation timestamp of the user.")
    private Timestamp createdAt;

    /**
     * Additional information about the user.
     * This relationship is managed by the user info entity.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_info_id")
    @ToString.Exclude
    private UserInfo userInfo;

    /**
     * Equals method for comparing User objects.
     * It compares based on the user ID, first name, last name, and creation timestamp.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(createdAt, user.createdAt);
    }

    /**
     * Generates the hash code for the User object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, createdAt);
    }
}
