package com.prgms.ywbook.rental.service.dto;

import com.prgms.ywbook.rental.domain.JoinedRental;

import java.util.List;
import java.util.stream.Collectors;

public record JoinedRentalResponses(List<JoinedRentalResponse> rentals) {

    public static JoinedRentalResponses of(List<JoinedRental> joinedRentals){
        List<JoinedRentalResponse> rentalsResponse = joinedRentals.stream().map(JoinedRentalResponse::new).collect(Collectors.toList());
        return new JoinedRentalResponses(rentalsResponse);
    }

    @Override
    public List<JoinedRentalResponse> rentals(){
        return List.copyOf(rentals);
    }
}
