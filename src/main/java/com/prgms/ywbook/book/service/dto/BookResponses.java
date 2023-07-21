package com.prgms.ywbook.book.service.dto;

import com.prgms.ywbook.book.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

public record BookResponses(List<BookResponse> books) {

    public static BookResponses of(List<Book> books) {
        List<BookResponse> bookResponse = books.stream().map(BookResponse::new).collect(Collectors.toList());
        return new BookResponses(bookResponse);
    }

    @Override
    public List<BookResponse> books() {
        return List.copyOf(books);
    }
}
