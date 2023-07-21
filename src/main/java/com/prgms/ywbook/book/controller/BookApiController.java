package com.prgms.ywbook.book.controller;

import com.prgms.ywbook.book.controller.mapper.ControllerBookMapper;
import com.prgms.ywbook.book.service.BookService;
import com.prgms.ywbook.book.service.dto.BookResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/book")
@RestController
public class BookApiController {
    private final BookService bookService;
    private final ControllerBookMapper mapper;

    public BookApiController(BookService bookService, ControllerBookMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<BookResponses> findAllAvailable() {
        BookResponses response = bookService.findAllAvailable();
        return ResponseEntity.ok(response);
    }
}
