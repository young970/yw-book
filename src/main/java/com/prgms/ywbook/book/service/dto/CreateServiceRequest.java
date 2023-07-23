package com.prgms.ywbook.book.service.dto;

import java.util.UUID;

public record CreateServiceRequest(UUID id, String title, String author) {
}
