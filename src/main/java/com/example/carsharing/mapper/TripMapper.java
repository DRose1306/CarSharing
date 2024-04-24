package com.example.carsharing.mapper;

import com.example.carsharing.dto.TripAfterCreationDto;
import com.example.carsharing.dto.TripCreateDto;
import com.example.carsharing.entity.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TripMapper {
    Trip toEntity(TripCreateDto tripCreateDto);
    TripAfterCreationDto toDto(Trip tripAfterCreation);
}
