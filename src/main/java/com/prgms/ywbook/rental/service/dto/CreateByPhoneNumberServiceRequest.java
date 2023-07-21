package com.prgms.ywbook.rental.service.dto;

import java.util.UUID;

public record CreateByPhoneNumberServiceRequest(UUID rentalId, String phoneNumber, UUID bookId) {
}
