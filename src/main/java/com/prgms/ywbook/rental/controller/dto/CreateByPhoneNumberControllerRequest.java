package com.prgms.ywbook.rental.controller.dto;

import java.util.List;
import java.util.UUID;

public record CreateByPhoneNumberControllerRequest(String phoneNumber, List<UUID> bookIds) {
}
