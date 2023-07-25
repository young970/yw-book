package com.prgms.ywbook.global;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerater implements IdGenerater{

    private UUIDGenerater() {
        throw new RuntimeException("생성 안돼!!");
    }

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
