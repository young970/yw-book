package com.prgms.ywbook.book.service;

import com.prgms.ywbook.book.domain.Book;
import com.prgms.ywbook.book.domain.BookRepository;
import com.prgms.ywbook.book.service.dto.BookResponses;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponses findAllAvailable() {
        return BookResponses.of(bookRepository.findAvailable(true));
    }
}
