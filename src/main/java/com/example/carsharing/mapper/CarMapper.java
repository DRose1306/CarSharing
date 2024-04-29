package com.example.carsharing.mapper;

import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;
import org.mapstruct.*;

import java.sql.Timestamp;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = Timestamp.class ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {
    @Mappings({
            @Mapping(target = "yearOfRelease", source = "yearOfRelease"),
            @Mapping(target = "licensePlate", source = "licensePlate"),
            @Mapping(target = "brand", source = "brand"),
            @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))"),
            @Mapping(target = "carId", ignore = true)
    })
    Car toEntity(CarCreateDto carCreateDto);

    @Mappings({
            @Mapping(target = "message", constant = "Car added"),
            @Mapping(target = "licensePlate", source = "licensePlate"),
            @Mapping(target = "brand", source = "brand"),
            @Mapping(target = "status", defaultValue = "AVAILABLE"),
            @Mapping(target = "createdAt", source = "createdAt"),
    })
    CarAfterCreationDto toDto(Car carAfterCreation);
}