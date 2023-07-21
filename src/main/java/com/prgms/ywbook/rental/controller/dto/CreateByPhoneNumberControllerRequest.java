package com.prgms.ywbook.rental.controller.dto;

import java.util.UUID;

public record CreateByPhoneNumberControllerRequest(String phoneNumber, UUID bookId) {
}
