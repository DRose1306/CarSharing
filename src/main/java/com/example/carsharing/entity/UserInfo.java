package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.DriverLicense;
import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_info")
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "user_info_id")
    private UUID userInfoId;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "driver_license")
    private DriverLicense driverLicense;

    @OneToOne(mappedBy = "userInfo")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "user_info_role",
            joinColumns = @JoinColumn(name = "user_info_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(userInfoId, userInfo.userInfoId) && Objects.equals(dateOfBirth, userInfo.dateOfBirth) && Objects.equals(phoneNumber, userInfo.phoneNumber) && Objects.equals(email, userInfo.email) && driverLicense == userInfo.driverLicense && Objects.equals(user, userInfo.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInfoId, dateOfBirth, phoneNumber, email, driverLicense, user);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", driverLicense=" + driverLicense +
                ", user=" + user +
                '}';
    }
}
