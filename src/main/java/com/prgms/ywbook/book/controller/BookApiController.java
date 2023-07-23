package com.prgms.ywbook.book.controller;

import com.prgms.ywbook.book.controller.dto.CreateControllerRequest;
import com.prgms.ywbook.book.controller.mapper.ControllerBookMapper;
import com.prgms.ywbook.book.service.BookService;
import com.prgms.ywbook.book.service.dto.BookResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateControllerRequest request){
        bookService.create(mapper.controllerDtoToServiceDto(request));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
