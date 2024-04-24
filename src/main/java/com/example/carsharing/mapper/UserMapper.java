package com.example.carsharing.mapper;

import com.example.carsharing.dto.UserAfterCreationDto;
import com.example.carsharing.dto.UserCreateDto;
import com.example.carsharing.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserCreateDto userCreateDto);

    UserAfterCreationDto toDto(User userAfterCreation);
}
