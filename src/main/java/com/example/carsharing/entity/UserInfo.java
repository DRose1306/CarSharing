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

    @OneToOne(mappedBy = "userInfo")
    @JoinColumn(name = "user_id")
    private User user;

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
        return Objects.equals(userInfoId, userInfo.userInfoId) && Objects.equals(user, userInfo.user) && Objects.equals(dateOfBirth, userInfo.dateOfBirth) && Objects.equals(phoneNumber, userInfo.phoneNumber) && Objects.equals(email, userInfo.email) && Objects.equals(password, userInfo.password) && driverLicense == userInfo.driverLicense && Objects.equals(roles, userInfo.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInfoId, user, dateOfBirth, phoneNumber, email, password, driverLicense, roles);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", user=" + user +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", driverLicense=" + driverLicense +
                ", roles=" + roles +
                '}';
    }
}
