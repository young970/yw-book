package com.prgms.ywbook.book.service;

import com.prgms.ywbook.book.domain.Author;
import com.prgms.ywbook.book.domain.Book;
import com.prgms.ywbook.book.domain.BookRepository;
import com.prgms.ywbook.book.domain.Title;
import com.prgms.ywbook.book.service.dto.BookResponses;
import com.prgms.ywbook.book.service.dto.CreateServiceRequest;
import com.prgms.ywbook.global.Generater;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final Generater generater;

    public BookService(BookRepository bookRepository, Generater generater) {
        this.bookRepository = bookRepository;
        this.generater = generater;
    }

    public BookResponses findAllAvailable() {
        return BookResponses.of(bookRepository.findAvailable(true));
    }

    public void create(CreateServiceRequest request) {
        Title title = new Title(request.title());
        Author author = new Author(request.author());
        bookRepository.insert(new Book(generater.idGenerate(), title, author, true));
    }

    @Transactional
    public void deleteById(UUID id) {
        bookRepository.deleteById(id);
    }
}
