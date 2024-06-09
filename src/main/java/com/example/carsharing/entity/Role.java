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
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * This class represents a role in the system.
 * It stores detailed information about the role
 * including the role name and associated authorities.
 * <p>
 * This class provides a representation of a role
 * with various attributes like name, authorities, etc.
 * <p>
 * This is used throughout the application to manage and persist role data.
 *
 * @author Urmat Bolotbek Uulu
 */
@Entity
@Getter
@Setter
@Table(name = "roles")
@NoArgsConstructor
@ToString
@Schema(description = "Entity representing a role in the carsharing system.")
public class Role {

    /**
     * Unique identifier of the role.
     * Generated using a custom UUID generator.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
    @Column(name = "role_id")
    @Schema(description = "Unique identifier of the role.")
    private UUID roleId;

    /**
     * Name of the role.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    @Schema(description = "Name of the role.")
    private RoleName roleName;

    /**
     * Set of users associated with this role.
     * This relationship is bidirectional and managed by the user entity.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    private Set<UserInfo> userInfos;

    /**
     * Set of authorities associated with this role.
     * This relationship is bidirectional and managed by the authority entity.
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
    @ToString.Exclude
    private Set<Authority> authorities;

    /**
     * Equals method for comparing Role objects.
     * It compares based on the role ID and role name.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) && roleName == role.roleName;
    }

    /**
     * Generates the hash code for the Role object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
