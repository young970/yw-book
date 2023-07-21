package com.prgms.ywbook.book.domain;

import com.prgms.ywbook.book.exception.InvalidLengthException;

public class Title {
    private final String title;

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    private void validate(String title) {
        if (title.length() > 40){
            throw new InvalidLengthException("책의 제목의 길이가 기준보다 초과했습니다.");
        }
    }

    public String getTitle() {
        return title;
    }
}
