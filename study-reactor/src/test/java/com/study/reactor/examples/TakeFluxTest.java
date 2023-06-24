package com.study.reactor.examples;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

class TakeFluxTest {
    @Test
    void take(){
        Flux<String> parkFlux = Flux
                .just("Yellowstone", "Yosemite", "Grand Canyon", "Zion")
                .take(3)
                .log();

        StepVerifier.create(parkFlux)
                .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
                .verifyComplete();
    }

    @Test
    void takeForAWhile(){
        Flux<String> parkFlux = Flux
                .just("Yellowstone", "Yosemite", "Grand Canyon", "Zion")
                .delayElements(Duration.ofMillis(100))
                .take(Duration.ofMillis(350))
                .log();

        StepVerifier.create(parkFlux)
                .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
                .verifyComplete();
    }
}
