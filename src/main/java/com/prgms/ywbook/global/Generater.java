package com.prgms.ywbook.global;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Generater {
    UUID idGenerate();
    LocalDateTime timeGenerate();
}
