package com.prgms.ywbook.rental.controller.mapper;

import com.prgms.ywbook.rental.controller.dto.CreateByPhoneNumberControllerRequest;
import com.prgms.ywbook.rental.service.dto.CreateByPhoneNumberServiceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ControllerRentalMapper {
    CreateByPhoneNumberServiceRequest controllerDtoToServiceDto(CreateByPhoneNumberControllerRequest request);

    @Mapping(target = "bookIds", source = "bookIds")
    default List<UUID> mapBookIds(List<UUID> bookIds) {
        return bookIds;
    }
}
