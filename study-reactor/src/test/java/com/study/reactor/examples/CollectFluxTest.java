package com.study.reactor.examples;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class CollectFluxTest {
    @Test
    void collectList(){
        Flux<String> fruitFlux = Flux
                .just("apple", "orange", "banana", "kiwi", "strawberry");

        Mono<List<String>> fruitListMono = fruitFlux.collectList();

        StepVerifier.create(fruitListMono)
                .expectNext(Arrays.asList(
                        "apple", "orange", "banana", "kiwi", "strawberry"))
                .verifyComplete();
    }

    @Test
    void collectMap(){
        Flux<String> fruitFlux = Flux
                .just("apple", "orange", "banana");

        Mono<Map<Character, String>> fruitMapMono = fruitFlux
                .collectMap(f -> f.charAt(0))
                .log();

        StepVerifier.create(fruitMapMono)
                .expectNextMatches(map -> map.size() == 3 &&
                        map.get('o').equals("orange") &&
                        map.get('a').equals("apple") &&
                        map.get('b').equals("banana"))
                .verifyComplete();
    }
}
