package com.prgms.ywbook.rental.service;

import com.prgms.ywbook.book.domain.Book;
import com.prgms.ywbook.book.domain.BookRepository;
import com.prgms.ywbook.global.exception.NotFoundEntityException;
import com.prgms.ywbook.member.domain.Member;
import com.prgms.ywbook.member.domain.MemberRepository;
import com.prgms.ywbook.member.domain.PhoneNumber;
import com.prgms.ywbook.rental.domain.JoinedRental;
import com.prgms.ywbook.rental.domain.Rental;
import com.prgms.ywbook.rental.domain.RentalRepository;
import com.prgms.ywbook.rental.service.dto.CreateByPhoneNumberServiceRequest;
import com.prgms.ywbook.rental.service.dto.FindBookResponses;
import com.prgms.ywbook.rental.service.dto.JoinedRentalResponses;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public RentalService(RentalRepository rentalRepository, MemberRepository memberRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void createByMemberPhoneNumber(CreateByPhoneNumberServiceRequest request) {
        Member member = memberRepository.findByNumber(request.phoneNumber())
                .orElseGet(() -> createMember(request.phoneNumber()));

        request.bookIds().forEach((id) -> {
                    Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("해당 책은 존재하지 않습니다."));
                    book.setAvailable(false);
                    bookRepository.update(book);

                    Rental rental = new Rental(request.getUUID(), member.getMemberId(), id, LocalDateTime.now());
                    rentalRepository.insert(rental);
                });
    }

    public FindBookResponses findRentalBookByNumber(String phoneNumber) {
        PhoneNumber number = new PhoneNumber(phoneNumber);
        List<JoinedRental> joinedRental = rentalRepository.findJoinedRentalByPhoneNumber(number);
        return FindBookResponses.of(joinedRental);
    }

    public JoinedRentalResponses findDelayBooks() {
        LocalDateTime now = LocalDateTime.now();
        return JoinedRentalResponses.of(rentalRepository.findJoinedRentalByRentalAt(now));
    }

    @Transactional
    public void deleteById(UUID rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new NotFoundEntityException("해당 대여 기록은 존재하지 않습니다."));

        Book book = bookRepository.findById(rental.getBookId())
                .orElseThrow(() -> new NotFoundEntityException("해당 대여 기록은 존재하지 않습니다."));

        book.setAvailable(true);
        bookRepository.update(book);

        rentalRepository.deleteById(rentalId);
    }

    private Member createMember(String phoneNumber) {
        return memberRepository.insert(new Member(UUID.randomUUID(), new PhoneNumber(phoneNumber)));
    }
}
