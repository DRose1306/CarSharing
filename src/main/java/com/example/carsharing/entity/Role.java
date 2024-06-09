package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.RoleName;
import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "roles")
@NoArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "role_id")
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleName roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    private Set<UserInfo> userInfos;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
    @ToString.Exclude
    private Set<Authority> authorities = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) && roleName == role.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
