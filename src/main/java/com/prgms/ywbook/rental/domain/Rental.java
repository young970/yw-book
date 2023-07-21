package com.prgms.ywbook.rental.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Rental {
    private final UUID id;
    private final UUID memberId;
    private final UUID bookId;
    private final LocalDateTime rentedAt;

    public Rental(UUID id, UUID memberId, UUID bookId, LocalDateTime rentedAt) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.rentedAt = rentedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public LocalDateTime getRentedAt() {
        return rentedAt;
    }
}
