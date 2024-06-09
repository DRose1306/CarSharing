package com.example.carsharing.mapper;

import com.example.carsharing.dto.PaymentAfterCreationDto;
import com.example.carsharing.dto.PaymentCreateDto;
import com.example.carsharing.entity.Payment;
import com.example.carsharing.mapper.util.GetNameFromUserUtil;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {GetNameFromUserUtil.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    /**
     * Maps PaymentCreateDto to Payment entity.
     *
     * @param paymentCreateDto The PaymentCreateDto object.
     * @return The mapped Payment object.
     */
    @Mappings({
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "paymentDate", source = "paymentDate"),
            @Mapping(target = "paymentMethod", source = "paymentMethod"),
            @Mapping(target = "paymentId", ignore = true)
    })
    Payment toEntity(PaymentCreateDto paymentCreateDto);

    /**
     * Maps Payment entity to PaymentAfterCreationDto.
     *
     * @param paymentAfterCreation The Payment object.
     * @return The mapped PaymentAfterCreationDto object.
     */
    @Mappings({
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "paymentDate", source = "paymentDate"),
            @Mapping(target = "paymentMethod", source = "paymentMethod"),
            @Mapping(target = "user", expression = "java(GetNameFromUserUtil.getName(paymentAfterCreation))"),
            @Mapping(target = "status", defaultValue = "false"),
    })
    PaymentAfterCreationDto toDto(Payment paymentAfterCreation);
}
