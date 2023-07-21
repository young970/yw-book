package com.prgms.ywbook.member.domain;

import java.util.UUID;

public class Member {
    private final UUID memberId;
    private final PhoneNumber phoneNumber;

    public Member(UUID memberId, PhoneNumber phoneNumber) {
        this.memberId = memberId;
        this.phoneNumber = phoneNumber;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public String getPhoneNumber() {
        return phoneNumber.getNumber();
    }
}
