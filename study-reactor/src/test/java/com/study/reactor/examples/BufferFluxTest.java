package com.study.reactor.examples;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

class BufferFluxTest {
    @Test
    void bufferTest(){
        Flux<String> fruitFlux = Flux
                .just("apple", "orange", "banana", "kiwi", "strawberry");

        Flux<List<String>> bufferedFlux = fruitFlux.buffer(3).log();

        StepVerifier.create(bufferedFlux)
                .expectNext(Arrays.asList("apple", "orange", "banana"))
                .expectNext(Arrays.asList("kiwi", "strawberry"))
                .verifyComplete();
    }

    @Test
    void bufferAndFlatMapTest(){
        Flux<String> fruitFlux = Flux
                .just("apple", "orange", "banana", "kiwi", "strawberry");

        Flux<String> bufferedFlux = fruitFlux
                .buffer(3)
                .flatMap(x -> Flux.fromIterable(x)
                        .map(String::toUpperCase)
                        .subscribeOn(Schedulers.parallel())
                        .log());

        List<String> expectedFruit = List
                .of("APPLE", "ORANGE", "BANANA", "KIWI", "STRAWBERRY");

        StepVerifier.create(bufferedFlux)
                .expectNextMatches(expectedFruit::contains)
                .expectNextMatches(expectedFruit::contains)
                .expectNextMatches(expectedFruit::contains)
                .expectNextMatches(expectedFruit::contains)
                .expectNextMatches(expectedFruit::contains)
                .verifyComplete();
    }
    @Test
    void bufferAllTest(){
        Flux<String> fruitFlux = Flux
                .just("apple", "orange", "banana", "kiwi", "strawberry");

        Flux<List<String>> bufferedFlux = fruitFlux.buffer().log();

        StepVerifier.create(bufferedFlux)
                .expectNext(Arrays.asList("apple", "orange", "banana", "kiwi", "strawberry"))
                .verifyComplete();
    }

}
