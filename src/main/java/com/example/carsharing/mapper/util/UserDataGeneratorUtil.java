package com.example.carsharing.mapper.util;

import com.example.carsharing.entity.Address;
import com.github.javafaker.Faker;

/**
 * Utility class for generating user data.
 */
public class UserDataGeneratorUtil {
    private static final Faker FAKER = new Faker();

    /**
     * Generates a random address.
     *
     * @return The generated address.
     */
    public static Address genAddress() {
        Address address = new Address();
        address.setStreet(FAKER.address().streetName());
        address.setHouseNumber(FAKER.address().buildingNumber());
        address.setCity(FAKER.address().cityName());
        address.setZipCode(FAKER.address().zipCode());
        address.setCountry(FAKER.address().country());
        return address;
    }

    /**
     * Generates a random phone number.
     *
     * @return The generated phone number.
     */
    public static String genPhoneNumber() {
        return FAKER.phoneNumber().phoneNumber();
    }
}
