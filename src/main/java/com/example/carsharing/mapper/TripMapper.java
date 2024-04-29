package com.example.carsharing.mapper;

import com.example.carsharing.dto.TripAfterCreationDto;
import com.example.carsharing.dto.TripCreateDto;
import com.example.carsharing.entity.Trip;
import com.example.carsharing.mapper.util.GetCarIdFromTripUtil;
import com.example.carsharing.mapper.util.GetNameFromUserUtil;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {GetNameFromUserUtil.class, GetCarIdFromTripUtil.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripMapper {
    @Mappings({
            @Mapping(target = "startTime", source = "startTime"),
            @Mapping(target = "endTime", source = "endTime"),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "car", ignore = true),
            @Mapping(target = "tripId", ignore = true)
    })
    Trip toEntity(TripCreateDto tripCreateDto);

    @Mappings({
            @Mapping(target = "cost", source = "cost"),
            @Mapping(target = "distance", source = "distance"),
            @Mapping(target = "user", expression = "java(GetNameFromUserUtil.getName(tripAfterCreation))"),
            @Mapping(target = "car", expression = "java(GetCarIdFromTripUtil.getCarId(tripAfterCreation))"),
    })
    TripAfterCreationDto toDto(Trip tripAfterCreation);
}
