package com.prgms.ywbook.rental.controller;

import com.prgms.ywbook.rental.controller.mapper.ControllerRentalMapper;
import com.prgms.ywbook.rental.service.RentalService;
import com.prgms.ywbook.rental.service.dto.FindBookResponses;
import com.prgms.ywbook.rental.service.dto.JoinedRentalResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/view/rental")
@Controller
public class RentalViewController {
    private final RentalService rentalService;
    private final ControllerRentalMapper mapper;

    public RentalViewController(RentalService rentalService, ControllerRentalMapper mapper) {
        this.rentalService = rentalService;
        this.mapper = mapper;
    }

    @GetMapping
    public String findRentalBookByNumber(@RequestParam String phoneNumber, Model model){
        FindBookResponses responses = rentalService.findRentalBookByNumber(phoneNumber);
        model.addAttribute("rentals", responses);
        return "return_books";
    }

    @GetMapping("/delay")
    public String findDelayBookList(Model model){
        JoinedRentalResponses responses = rentalService.findDelayBooks();
        model.addAttribute("responses", responses);
        return "management";
    }
}
