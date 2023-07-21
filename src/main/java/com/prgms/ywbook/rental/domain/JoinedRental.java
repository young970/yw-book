package com.prgms.ywbook.rental.domain;

import com.prgms.ywbook.book.domain.Book;
import com.prgms.ywbook.member.domain.Member;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class JoinedRental {
    private final UUID id;
    private final Member member;
    private final Book book;
    private final LocalDateTime rentedAt;

    public JoinedRental(UUID id, Member member, Book book, LocalDateTime rentedAt) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.rentedAt = rentedAt;
    }

    public UUID getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getRentedAt() {
        return rentedAt;
    }

    public String getRemainingTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime Deadline = rentedAt.plusDays(14);
        Duration remainingTime = Duration.between(now, Deadline);
        long days = remainingTime.toDays();
        long hours = remainingTime.toHoursPart();
        long minutes = remainingTime.toMinutesPart();

        return days + "일 " + hours + "시간 " + minutes + "분";
    }
}
