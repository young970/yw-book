package com.prgms.ywbook.book.controller.mapper;

import com.prgms.ywbook.book.controller.dto.CreateControllerRequest;
import com.prgms.ywbook.book.service.dto.CreateServiceRequest;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-23T23:23:23+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class ControllerBookMapperImpl implements ControllerBookMapper {

    @Override
    public CreateServiceRequest controllerDtoToServiceDto(CreateControllerRequest request) {
        if ( request == null ) {
            return null;
        }

        String title = null;
        String author = null;

        title = request.title();
        author = request.author();

        UUID id = createUUID();

        CreateServiceRequest createServiceRequest = new CreateServiceRequest( id, title, author );

        return createServiceRequest;
    }
}
