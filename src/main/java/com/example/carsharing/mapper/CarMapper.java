package com.example.carsharing.mapper;

import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    Car toEntity(CarCreateDto carCreateDto);
    CarAfterCreationDto toDto(Car carAfterCreation);
}