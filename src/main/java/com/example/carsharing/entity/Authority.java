package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.AuthorityName;
import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "authorities")
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "auth_id")
    private UUID authorityId;

    @Column(name = "authority")
    private AuthorityName authority;

    @ManyToMany(mappedBy = "authorities",
            fetch = FetchType.LAZY)
    private Set<Role> roles;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(authorityId, authority1.authorityId) && authority == authority1.authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityId, authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authorityId=" + authorityId +
                ", authority=" + authority +
                ", roles=" + roles +
                '}';
    }
}
