package com.prgms.ywbook.rental.controller.mapper;

import com.prgms.ywbook.rental.controller.dto.CreateByPhoneNumberControllerRequest;
import com.prgms.ywbook.rental.service.dto.CreateByPhoneNumberServiceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ControllerRentalMapper {
    @Mapping(target = "rentalId", expression = "java(createUUID())")
    CreateByPhoneNumberServiceRequest controllerDtoToServiceDto(CreateByPhoneNumberControllerRequest request);

    default UUID createUUID() {
        return UUID.randomUUID();
    }
}
