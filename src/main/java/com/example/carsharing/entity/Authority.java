package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.AuthorityName;
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
 * This class represents an authority in the system.
 * It stores detailed information about the authority
 * which can be linked to roles.
 * <p>
 * This class provides a representation of an authority
 * with various attributes like authority name, roles, etc.
 * <p>
 * This is used throughout the application to manage and persist authority data.
 *
 * @author Urmat Bolotbek Uulu
 */
@Entity
@Getter
@Setter
@Table(name = "authorities")
@NoArgsConstructor
@ToString
@Schema(description = "Entity representing an authority in the carsharing system.")
public class Authority {

    /**
     * Unique identifier of the authority.
     * Generated using a custom UUID generator.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
    @Column(name = "auth_id")
    @Schema(description = "Unique identifier of the authority.")
    private UUID authorityId;

    /**
     * Name of the authority.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    @Schema(description = "Name of the authority.")
    private AuthorityName authority;

    /**
     * Set of roles associated with this authority.
     * This relationship is bidirectional and managed by the role entity.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Schema(description = "Set of roles associated with this authority.")
    private Set<Role> roles;

    /**
     * Equals method for comparing Authority objects.
     * It compares based on the authority ID and authority name.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(authorityId, authority1.authorityId) &&
                authority == authority1.authority;
    }

    /**
     * Generates the hash code for the Authority object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(authorityId, authority);
    }
}
