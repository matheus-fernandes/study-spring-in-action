package com.study.reactor.examples;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FilterFluxTest {
    @Test
    void filter(){
        Flux<Integer> oddNumbersFlux = Flux
                .range(1, 5)
                .filter(n -> n % 2 == 1)
                .log();

        StepVerifier.create(oddNumbersFlux)
                .expectNext(1, 3, 5)
                .verifyComplete();
    }
}
