package com.prgms.ywbook.book.controller;

import com.prgms.ywbook.book.controller.mapper.ControllerBookMapper;
import com.prgms.ywbook.book.service.BookService;
import com.prgms.ywbook.book.service.dto.BookResponses;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookViewController {
    private final BookService bookService;
    private final ControllerBookMapper mapper;

    public BookViewController(BookService bookService, ControllerBookMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping
    public String findAllAvailable(Model model) {
        BookResponses response = bookService.findAllAvailable();
        model.addAttribute("book", response);
        return "books";
    }
}
