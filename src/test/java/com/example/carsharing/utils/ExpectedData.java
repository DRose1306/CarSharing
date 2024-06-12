package com.example.carsharing.utils;

import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.dto.PaymentCreateDto;
import com.example.carsharing.dto.TripCreateDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.*;
import com.example.carsharing.entity.enums.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class ExpectedData {

    public static Payment returnPaymentById() {
        Payment payment = new Payment();
        payment.setPaymentId(UUID.fromString("92683b96-579e-4fee-9329-b442639582e7"));
        payment.setAmount(100.00);
        payment.setPaymentDate(LocalDateTime.parse("2024-04-10T19:00"));
        payment.setPaymentMethod(PaymentMethod.VISA);
        payment.setStatus(true);

        return payment;
    }


    public static PaymentCreateDto returnPaymentCreateDto() {
        PaymentCreateDto paymentCreateDto = new PaymentCreateDto();
        paymentCreateDto.setAmount(200.0);
        paymentCreateDto.setPaymentDate("2024-04-10T19:00:00");
        paymentCreateDto.setPaymentMethod(PaymentMethod.PAYPAL);
        paymentCreateDto.setUserId("55035fe9-37e3-466f-ba4a-197f23fc5700");

        return paymentCreateDto;
    }

    public static Trip returnTripById() {
        Trip trip = new Trip();
        trip.setTripId(UUID.fromString("0628ad72-9f21-4dd4-98ea-ee08bcfbd36e"));
        trip.setStartTime(LocalDateTime.parse("2024-08-10T00:00:00"));
        trip.setEndTime(LocalDateTime.parse("2024-08-25T17:30:00"));
        trip.setDistance(500);
        trip.setCost(new BigDecimal("500.00"));

        return trip;
    }

    public static TripCreateDto returnTripCreateDto() {
        TripCreateDto tripCreateDto = new TripCreateDto();
        tripCreateDto.setStartTime("2024-08-10T00:00:00");
        tripCreateDto.setEndTime("2024-08-10T14:00:00");
        tripCreateDto.setUserId("cd8edecd-0d27-4228-8fe6-911c1cf7fd7c");
        tripCreateDto.setCarId("ef6869b7-2402-48c7-bff4-141563be2d8c");

        return tripCreateDto;
    }

    public static User returnUserById() {
        User user = new User();
        user.setUserId(UUID.fromString("cd8edecd-0d27-4228-8fe6-911c1cf7fd7c"));
        user.setFirstName("Olivia");
        user.setLastName("Martinez");

        return user;
    }

    public static UserRegistrationDto returnUserRegistrationDto() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setFirstName("Mary");
        userRegistrationDto.setLastName("Brown");
        userRegistrationDto.setDateOfBirth("1988-07-07");
        userRegistrationDto.setEmail("mary@example.com");
        userRegistrationDto.setPassword("mary_b");
        userRegistrationDto.setDriverLicense(DriverLicense.B);
        userRegistrationDto.setDriverLicenseIdentifier("DE884321098765");

        return userRegistrationDto;
    }

    public static Car returnCarById() {
        Car car = new Car();
        car.setCarId(UUID.fromString("2e88a78d-b4a7-4a00-b590-4d0f7abe6c04"));
        car.setYearOfRelease("2019");
        car.setLicensePlate("OS-OS5");
        car.setStatus(CarStatus.IN_USE);
        car.setBrand(CarBrand.HONDA);
        car.setCreatedAt(new Timestamp(System.currentTimeMillis()));


        return car;
    }

    public static CarCreateDto returnCarCreateDto() {
        CarCreateDto carCreateDto = new CarCreateDto();
        carCreateDto.setYearOfRelease("2020");
        carCreateDto.setLicensePlate("OS-KG13");
        carCreateDto.setBrand(CarBrand.HYUNDAI);

        return carCreateDto;
    }
}
