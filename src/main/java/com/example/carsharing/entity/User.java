package com.example.carsharing.entity;


import com.example.carsharing.entity.enums.DriverLicense;

import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userInfo, user.userInfo) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userInfo, firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userInfo=" + userInfo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}