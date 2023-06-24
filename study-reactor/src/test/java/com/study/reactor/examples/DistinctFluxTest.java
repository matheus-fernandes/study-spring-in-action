package com.study.reactor.examples;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class DistinctFluxTest {
    @Test
    void distinct(){
        Flux<String> animalFlux = Flux
                .just("dog", "cat", "dog", "bird", "bird")
                .distinct()
                .log();

        StepVerifier.create(animalFlux)
                .expectNext("dog", "cat", "bird")
                .verifyComplete();
    }
}
