package com.prgms.ywbook.rental.service.dto;

import java.util.List;
import java.util.UUID;

public record CreateByPhoneNumberServiceRequest(String phoneNumber, List<UUID> bookIds) {
}
