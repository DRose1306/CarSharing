package com.example.carsharing.entity;

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
 * This class represents an address in the system.
 * It stores detailed information about the address
 * which can be linked to users or other entities.
 * <p>
 * This class provides a representation of an address
 * with various attributes like street, house number, city, etc.
 * <p>
 * This is used throughout the application to manage and persist address data.
 *
 * @author Urmat Bolotbek Uulu
 */
@Entity
@Getter
@Setter
@Table(name = "addresses")
@NoArgsConstructor
@ToString
@Schema(description = "Entity representing an address in the carsharing system.")
public class Address {

    /**
     * Unique identifier of the address.
     * Generated using a custom UUID generator.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
    @Column(name = "address_id")
    @Schema(description = "Unique identifier of the address.")
    private UUID addressId;

    /**
     * Street name of the address.
     */
    @Column(name = "street")
    @Schema(description = "Street name of the address.")
    private String street;

    /**
     * House number of the address.
     */
    @Column(name = "house_number")
    @Schema(description = "House number of the address.")
    private String houseNumber;

    /**
     * City of the address.
     */
    @Column(name = "city")
    @Schema(description = "City of the address.")
    private String city;

    /**
     * ZIP code of the address.
     */
    @Column(name = "zip_code")
    @Schema(description = "ZIP code of the address.")
    private String zipCode;

    /**
     * Country of the address.
     */
    @Column(name = "country")
    @Schema(description = "Country of the address.")
    private String country;

    /**
     * Set of user information objects linked to this address.
     * This relationship is bidirectional and managed by the address entity.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @Schema(description = "Set of user information objects linked to this address.")
    private Set<UserInfo> userInfos;

    /**
     * Equals method for comparing Address objects.
     * It compares based on the address ID and other address fields.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId) &&
                Objects.equals(street, address.street) &&
                Objects.equals(houseNumber, address.houseNumber) &&
                Objects.equals(city, address.city) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(country, address.country);
    }

    /**
     * Generates the hash code for the Address object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(addressId, street, houseNumber, city, zipCode, country);
    }
}
