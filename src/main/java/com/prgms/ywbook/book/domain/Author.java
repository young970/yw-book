package com.prgms.ywbook.book.domain;

import com.prgms.ywbook.book.exception.InvalidLengthException;

public class Author {
    private final String author;

    public Author(String author) {
        validate(author);
        this.author = author;
    }

    private void validate(String author) {
        if (author.length() > 10){
            throw new InvalidLengthException("작가명의 길이가 기준을 초과합니다.");
        }
    }

    public String getAuthor() {
        return author;
    }
}
