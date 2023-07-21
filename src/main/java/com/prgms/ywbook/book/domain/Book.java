package com.prgms.ywbook.book.domain;

import java.util.UUID;

public class Book {
    private final UUID id;
    private final Title title;
    private final Author author;
    private final boolean available;

    public Book(UUID id, Title title, Author author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title.getTitle();
    }

    public String getAuthor() {
        return author.getAuthor();
    }

    public boolean isAvailable() {
        return available;
    }
}
