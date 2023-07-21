package com.prgms.ywbook.book.service.dto;

import com.prgms.ywbook.book.domain.Book;

public record BookResponse(String bookId, String title, String author) {
    public BookResponse(Book book) {
        this(book.getId().toString(), book.getTitle(), book.getAuthor());
    }
}
