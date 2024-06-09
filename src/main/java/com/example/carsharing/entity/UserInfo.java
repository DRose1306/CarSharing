package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.DriverLicense;
import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * This class represents additional information about a user.
 * It includes details such as date of birth, phone number, and address.
 * <p>
 * This class provides detailed information about a user
 * including date of birth, phone number, and driver's license information.
 *
 * @author Urmat Bolotbek Uulu
 */
@Entity
@Getter
@Setter
@Table(name = "user_info")
@NoArgsConstructor
@ToString
@Schema(description = "Entity representing additional information about a user in the carsharing system.")
public class UserInfo {

    /**
     * Unique identifier of the user information.
     * Generated using a custom UUID generator.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "user_info_id")
    @Schema(description = "Unique identifier of the user information.")
    private UUID userInfoId;

    /**
     * Date of birth of the user.
     */
    @Column(name = "date_of_birth")
    @Schema(description = "Date of birth of the user.")
    private LocalDate dateOfBirth;

    /**
     * Phone number of the user.
     */
    @Column(name = "phone_number")
    @Schema(description = "Phone number of the user.")
    private String phoneNumber;

    /**
     * Email address of the user.
     */
    @Column(name = "email")
    @Schema(description = "Email address of the user.")
    private String email;

    /**
     * Login name of the user.
     */
    @Column(name = "user_login")
    @Schema(description = "Login name of the user.")
    private String login;

    /**
     * Password of the user.
     */
    @Column(name = "user_password")
    @Schema(description = "Password of the user.")
    private String password;

    /**
     * Card number of the user.
     */
    @Column(name = "card_number")
    @Schema(description = "Card number of the user.")
    private String cardNumber;

    /**
     * Driver's license type of the user.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "driver_license")
    @Schema(description = "Driver's license type of the user.")
    private DriverLicense driverLicense;

    /**
     * Identifier of the driver's license of the user.
     */
    @Column(name = "driver_licence_id")
    @Schema(description = "Identifier of the driver's license of the user.")
    private String driverLicenseIdentifier;

    /**
     * Address of the user.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Schema(description = "Address of the user.")
    private Address address;

    /**
     * Roles assigned to the user.
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_info_role",
            joinColumns = @JoinColumn(name = "user_info_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles;

    /**
     * Equals method for comparing UserInfo objects.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(userInfoId, userInfo.userInfoId) &&
                Objects.equals(dateOfBirth, userInfo.dateOfBirth) &&
                Objects.equals(phoneNumber, userInfo.phoneNumber) &&
                Objects.equals(email, userInfo.email) &&
                Objects.equals(login, userInfo.login) &&
                Objects.equals(password, userInfo.password) &&
                driverLicense == userInfo.driverLicense &&
                Objects.equals(driverLicenseIdentifier, userInfo.driverLicenseIdentifier);
    }

    /**
     * Generates the hash code for the UserInfo object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(userInfoId, dateOfBirth, phoneNumber, email, login, password, driverLicense, driverLicenseIdentifier);
    }
}
