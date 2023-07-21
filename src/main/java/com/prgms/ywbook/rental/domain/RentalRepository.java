package com.prgms.ywbook.rental.domain;

import com.prgms.ywbook.member.domain.PhoneNumber;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface RentalRepository {
    Rental insert(Rental rental);

    List<JoinedRental> findJoinedRentalByPhoneNumber(PhoneNumber number);

    List<JoinedRental> findJoinedRentalByRentalAt(LocalDateTime time);

    void deleteById(UUID rentalId);
}
