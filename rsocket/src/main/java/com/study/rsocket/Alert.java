package com.study.rsocket;

import java.time.Instant;

public record Alert(Level level, String orderedBy, Instant orderedAt) {
    public enum Level{
        YELLOW, ORANGE, RED, BLACK
    }
}
