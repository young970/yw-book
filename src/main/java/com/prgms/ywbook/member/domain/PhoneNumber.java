package com.prgms.ywbook.member.domain;

import com.prgms.ywbook.member.exception.InvalidNumberException;

public class PhoneNumber {
    private final String number;

    public PhoneNumber(String number) {
        validate(number);
        this.number = number;
    }

    private void validate(String number) {
        if (number.length() > 11) throw new InvalidNumberException("전화번호 양식이 아닙니다.");

        try {
            Integer.parseInt(number);
        }catch (NumberFormatException e){
            throw new InvalidNumberException("전화번호 양식이 아닙니다.");
        }
    }

    public String getNumber() {
        return number;
    }
}
