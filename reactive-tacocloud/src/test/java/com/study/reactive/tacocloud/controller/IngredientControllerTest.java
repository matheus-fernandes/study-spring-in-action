package com.study.reactive.tacocloud.controller;

import com.study.reactive.tacocloud.config.PostgreContainerConfig;
import com.study.reactive.tacocloud.entity.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(PostgreContainerConfig.class)
class IngredientControllerTest {

    @Value("${server.port}")
    private String port;

    private WebClient webClient;

    @BeforeEach
    void init(){
        webClient = WebClient.create("http://localhost:" + port );
    }

    @Test
    void testRetrieveIngredient(){
        Mono<Ingredient> ingredientMono = webClient.get()
                .uri("/ingredients/TMT")
                .retrieve()
                .bodyToMono(Ingredient.class)
                .log();


        StepVerifier.create(ingredientMono)
                .assertNext(ingredient -> assertEquals("Tomato", ingredient.getName()));
    }

    @Test
    void testExchangeIngredientUnknown(){
        Mono<Ingredient> ingredientMono = webClient.get()
                .uri("/ingredients/TMT")
                .exchangeToMono(cr -> {
                    if (cr.headers().header("X_UNAVAILABLE").contains("true")){
                        return Mono.empty();
                    }
                    return Mono.just(cr);
                })
                .flatMap(cr -> cr.bodyToMono(Ingredient.class))
                .log();

        StepVerifier.create(ingredientMono)
                .expectNextMatches(ingredient -> true)
                .expectError(TimeoutException.class);
    }
}
