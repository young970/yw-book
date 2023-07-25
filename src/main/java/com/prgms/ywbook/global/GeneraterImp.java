package com.prgms.ywbook.global;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class GeneraterImp implements Generater {

    private GeneraterImp() {

    }

    @Override
    public UUID idGenerate() {
        return UUID.randomUUID();
    }

    @Override
    public LocalDateTime timeGenerate() {
        return LocalDateTime.now();
    }
}
