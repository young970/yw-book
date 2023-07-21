package com.prgms.ywbook.member.domain;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {
    Optional<Member> findById(UUID memberId);

    Optional<Member> findByNumber(String phoneNumber);

    Member insert(Member member);
}
