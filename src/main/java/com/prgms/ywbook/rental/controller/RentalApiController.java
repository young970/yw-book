package com.prgms.ywbook.rental.controller;

import com.prgms.ywbook.rental.controller.dto.CreateByPhoneNumberControllerRequest;
import com.prgms.ywbook.rental.controller.mapper.ControllerRentalMapper;
import com.prgms.ywbook.rental.service.RentalService;
import com.prgms.ywbook.rental.service.dto.FindBookResponses;
import com.prgms.ywbook.rental.service.dto.JoinedRentalResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/rental")
@RestController
public class RentalApiController {
    private final RentalService rentalService;
    private final ControllerRentalMapper mapper;

    public RentalApiController(RentalService rentalService, ControllerRentalMapper mapper) {
        this.rentalService = rentalService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> createByMemberPhoneNumber(@RequestBody CreateByPhoneNumberControllerRequest request){
        rentalService.createByMemberPhoneNumber(mapper.controllerDtoToServiceDto(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/number")
    public ResponseEntity<FindBookResponses> findRentalBookByNumber(@RequestParam String phoneNumber){
        FindBookResponses responses = rentalService.findRentalBookByNumber(phoneNumber);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/delay")
    public ResponseEntity<JoinedRentalResponses> findDelayBookList(){
        JoinedRentalResponses responses = rentalService.findDelayBooks();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{rentalId}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID rentalId){
        rentalService.deleteById(rentalId);
        return ResponseEntity.ok().build();
    }

}
