package com.prgms.ywbook.rental.service.dto;

import com.prgms.ywbook.rental.domain.JoinedRental;

import java.util.UUID;

public record FindBookResponse(UUID rentalId, String title, String author, String remainingTime) {
    public FindBookResponse(JoinedRental joinedRental) {
        this(joinedRental.getId(), joinedRental.getBook().getTitle(), joinedRental.getBook().getAuthor(), joinedRental.getRemainingTime());
    }
}
