package com.prgms.ywbook.rental.service.dto;

import com.prgms.ywbook.rental.domain.JoinedRental;

import java.util.List;
import java.util.stream.Collectors;

public record FindBookResponses(List<FindBookResponse> responses) {

    public static FindBookResponses of(List<JoinedRental> joinedRentals) {
        List<FindBookResponse> response = joinedRentals.stream().map(FindBookResponse::new).collect(Collectors.toList());
        return new FindBookResponses(response);
    }

    @Override
    public List<FindBookResponse> responses() {
        return List.copyOf(responses);
    }
}
