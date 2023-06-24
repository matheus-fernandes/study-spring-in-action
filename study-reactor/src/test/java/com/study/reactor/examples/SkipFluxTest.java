package com.study.reactor.examples;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

class SkipFluxTest {
    @Test
    void skipAFew(){
        Flux<String> countFlux = Flux
                .just("one", "two", "skip a few", "ninety nine", "one hundred")
                .skip(3)
                .log();

        StepVerifier.create(countFlux)
                .expectNext("ninety nine", "one hundred")
                .verifyComplete();
    }

    @Test
    void skipAFewSeconds(){
        Flux<String> countFlux = Flux
                .just("one", "two", "skip a few", "ninety nine", "one hundred")
                .delayElements(Duration.ofMillis(100))
                .skip(Duration.ofMillis(350))
                .log();

        StepVerifier.create(countFlux)
                .expectNext("ninety nine", "one hundred")
                .verifyComplete();
    }
}
