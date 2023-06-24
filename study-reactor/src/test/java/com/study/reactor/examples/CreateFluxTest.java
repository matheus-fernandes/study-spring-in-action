package com.study.reactor.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
class CreateFluxTest {
    @Test
    void createFluxJust() {
        Flux<String> fruitFlux = Flux
                .just("Apple", "Orange", "Grape", "Banana", "Strawberry")
                .log();

        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    void createFluxFromArray(){
        Flux<String> fruitFlux = Flux
                .fromArray(new String[]{"Apple", "Orange", "Grape", "Banana", "Strawberry"})
                .log();

        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    void createFluxFromIterable(){
        Flux<String> fruitFlux = Flux
                .fromIterable(List.of("Apple", "Orange", "Grape", "Banana", "Strawberry"))
                .log();

        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    void createFluxFromStream(){
        Flux<String> fruitFlux = Flux
                .fromStream(Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry"))
                .log();

        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    void createFluxRange(){
        Flux<Integer> rangeFlux = Flux
                .range(1, 5)
                .log();

        StepVerifier.create(rangeFlux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .verifyComplete();
    }

    @Test
    void createFluxInterval(){
        Flux<Long> intervalFlux = Flux
                .interval(Duration.ofSeconds(1))
                .take(5)
                .log();

        intervalFlux.subscribe(i -> log.debug(i.toString()));

        StepVerifier.create(intervalFlux)
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L)
                .verifyComplete();
    }
}
