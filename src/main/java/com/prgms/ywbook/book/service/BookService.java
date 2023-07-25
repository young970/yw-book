package com.prgms.ywbook.book.service;

import com.prgms.ywbook.book.domain.Author;
import com.prgms.ywbook.book.domain.Book;
import com.prgms.ywbook.book.domain.BookRepository;
import com.prgms.ywbook.book.domain.Title;
import com.prgms.ywbook.book.service.dto.BookResponses;
import com.prgms.ywbook.book.service.dto.CreateServiceRequest;
import com.prgms.ywbook.global.IdGenerater;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final IdGenerater idGenerater;

    public BookService(BookRepository bookRepository, IdGenerater idGenerater) {
        this.bookRepository = bookRepository;
        this.idGenerater = idGenerater;
    }

    public BookResponses findAllAvailable() {
        return BookResponses.of(bookRepository.findAvailable(true));
    }

    public void create(CreateServiceRequest request) {
        Title title = new Title(request.title());
        Author author = new Author(request.author());
        bookRepository.insert(new Book(idGenerater.generate(), title, author, true));
    }

    @Transactional
    public void deleteById(UUID id) {
        bookRepository.deleteById(id);
    }
}
