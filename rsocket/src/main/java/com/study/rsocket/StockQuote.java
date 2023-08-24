package com.study.rsocket;

import java.math.BigDecimal;
import java.time.Instant;

public record StockQuote(String symbol, BigDecimal price, Instant timestamp) {
}
