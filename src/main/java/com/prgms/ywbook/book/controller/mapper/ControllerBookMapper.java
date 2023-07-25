package com.prgms.ywbook.book.controller.mapper;

import com.prgms.ywbook.book.controller.dto.CreateControllerRequest;
import com.prgms.ywbook.book.service.dto.CreateServiceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ControllerBookMapper {
    CreateServiceRequest controllerDtoToServiceDto(CreateControllerRequest request);
}
