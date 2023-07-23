package com.prgms.ywbook.book.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {
    Optional<Book> findById(UUID bookId);
    Book insert(Book book);
    List<Book> findAll();
    List<Book> findAvailable(boolean available);
    Book update(Book book);
    void deleteById(UUID id);
}
