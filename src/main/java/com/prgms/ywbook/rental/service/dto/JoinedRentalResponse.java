package com.prgms.ywbook.rental.service.dto;

import com.prgms.ywbook.rental.domain.JoinedRental;

import java.time.LocalDateTime;
import java.util.UUID;

public record JoinedRentalResponse(UUID rentalId, LocalDateTime rentedAt, String phoneNumber, String title, String author) {
    public JoinedRentalResponse(JoinedRental joinedRental) {
        this(joinedRental.getId(), joinedRental.getRentedAt(), joinedRental.getMember().getPhoneNumber(), joinedRental.getBook().getTitle(), joinedRental.getBook().getAuthor());
    }
}
