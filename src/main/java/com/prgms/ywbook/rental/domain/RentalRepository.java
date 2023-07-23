package com.prgms.ywbook.rental.domain;

import com.prgms.ywbook.member.domain.PhoneNumber;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentalRepository {
    Rental insert(Rental rental);

    Optional<Rental> findById(UUID rentalId);

    List<Rental> findByRentedAt(LocalDateTime time);

    Optional<JoinedRental> findJoinedRentalById(UUID joinedRentalId);

    List<JoinedRental> findJoinedRentalByPhoneNumber(PhoneNumber number);

    List<JoinedRental> findJoinedRentalByRentalAt(LocalDateTime time);

    void deleteById(UUID rentalId);
}
