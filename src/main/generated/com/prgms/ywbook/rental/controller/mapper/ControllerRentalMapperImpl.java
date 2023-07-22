package com.prgms.ywbook.rental.controller.mapper;

import com.prgms.ywbook.rental.controller.dto.CreateByPhoneNumberControllerRequest;
import com.prgms.ywbook.rental.service.dto.CreateByPhoneNumberServiceRequest;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-23T02:49:18+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class ControllerRentalMapperImpl implements ControllerRentalMapper {

    @Override
    public CreateByPhoneNumberServiceRequest controllerDtoToServiceDto(CreateByPhoneNumberControllerRequest request) {
        if ( request == null ) {
            return null;
        }

        String phoneNumber = null;
        List<UUID> bookIds = null;

        phoneNumber = request.phoneNumber();
        bookIds = mapBookIds( request.bookIds() );

        CreateByPhoneNumberServiceRequest createByPhoneNumberServiceRequest = new CreateByPhoneNumberServiceRequest( phoneNumber, bookIds );

        return createByPhoneNumberServiceRequest;
    }
}
