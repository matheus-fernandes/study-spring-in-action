package com.study.rsocket;

import java.math.BigDecimal;

public record Gratuity(BigDecimal billTotal, int percent, BigDecimal gratuity) {
}
