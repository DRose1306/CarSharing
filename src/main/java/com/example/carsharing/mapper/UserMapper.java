package com.example.carsharing.mapper;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;
import com.example.carsharing.entity.UserInfo;
import com.example.carsharing.mapper.util.CardGeneratorUtil;
import com.example.carsharing.mapper.util.DateFormatterUtil;
import com.example.carsharing.mapper.util.UserDataGeneratorUtil;
import org.mapstruct.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {Timestamp.class, CardGeneratorUtil.class, DateFormatterUtil.class, UserDataGeneratorUtil.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    /**
     * Maps UserRegistrationDto to User entity.
     *
     * @param userRegistrationDto The UserRegistrationDto object.
     * @return The mapped User object.
     */
    @Mappings({
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))"),
            @Mapping(target = "userInfo", source = "userRegistrationDto"),
            @Mapping(target = "userInfo.login", ignore = true),
            @Mapping(target = "userInfo.password", ignore = true),
            @Mapping(target = "userId", ignore = true)
    })
    User toEntity(UserRegistrationDto userRegistrationDto);

    /**
     * Generates additional user information after mapping.
     *
     * @param user                The mapped User object.
     * @param userRegistrationDto The UserRegistrationDto object.
     */
    @AfterMapping
    default void generateUserInfo(@MappingTarget User user, UserRegistrationDto userRegistrationDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setLogin(generateUsername(userRegistrationDto.getFirstName()));
        userInfo.setPassword(encoder.encode(userRegistrationDto.getPassword()));
        userInfo.setEmail(userRegistrationDto.getEmail());
        userInfo.setDateOfBirth(LocalDate.parse(userRegistrationDto.getDateOfBirth()));
        userInfo.setAddress(UserDataGeneratorUtil.genAddress());
        userInfo.setPhoneNumber(UserDataGeneratorUtil.genPhoneNumber());
        userInfo.setCardNumber(CardGeneratorUtil.generateCreditCardDetails());
        userInfo.setDriverLicense(userRegistrationDto.getDriverLicense());
        userInfo.setDriverLicenseIdentifier(userRegistrationDto.getDriverLicenseIdentifier());
        user.setUserInfo(userInfo);
    }

    /**
     * Maps User entity to UserAfterRegistrationDto.
     *
     * @param user The User object.
     * @return The mapped UserAfterRegistrationDto object.
     */
    @Mappings({
            @Mapping(target = "operation", constant = "CREATING"),
            @Mapping(target = "status", constant = "SUCCESSFUL"),
            @Mapping(target = "login", source = "userInfo.login"),
            @Mapping(target = "password", source = "userInfo.password"),
            @Mapping(target = "createdAt", expression = "java(DateFormatterUtil.formatDataByCountry(user.getCreatedAt(), user.getUserInfo().getAddress().getCountry()))")
    })
    UserAfterRegistrationDto toDto(User user);

    static String generateUsername(String name) {
        return name + "_" + UUID.randomUUID().toString().charAt(1);
    }
}
