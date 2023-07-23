package com.prgms.ywbook.book.service;

import com.prgms.ywbook.book.domain.Author;
import com.prgms.ywbook.book.domain.Book;
import com.prgms.ywbook.book.domain.BookRepository;
import com.prgms.ywbook.book.domain.Title;
import com.prgms.ywbook.book.service.dto.BookResponses;
import com.prgms.ywbook.book.service.dto.CreateServiceRequest;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponses findAllAvailable() {
        return BookResponses.of(bookRepository.findAvailable(true));
    }

    public void create(CreateServiceRequest request) {
        Title title = new Title(request.title());
        Author author = new Author(request.author());
        bookRepository.insert(new Book(request.id(), title, author, true));
    }
}
