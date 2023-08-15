package com.study.reactive.tacocloud.controller;

import com.study.reactive.tacocloud.config.PostgreContainerConfig;
import com.study.reactive.tacocloud.entity.Taco;
import com.study.reactive.tacocloud.repository.TacoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(PostgreContainerConfig.class)
class TacoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TacoRepository tacoRepository;

    @Test
    public void shouldReturnRecentTacos(){
        webTestClient.get().uri("/api/tacos?recent")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].id").isEqualTo("123456789")
                .jsonPath("$[0].name").isEqualTo("Taco 1")
                .jsonPath("$[1].id").isEqualTo("321654987")
                .jsonPath("$[1].name").isEqualTo("Taco 2")
                .jsonPath("$[11].id").isEqualTo("444887878")
                .jsonPath("$[11].name").isEqualTo("Taco 12")
                .jsonPath("$[12]").doesNotExist();

    }

    @Test
    public void shouldSaveTaco(){
        Taco unsavedTaco = new Taco("New Taco");
        unsavedTaco.setId(123456789L);

        Mono<Taco> unsavedTacoMono = Mono.just(unsavedTaco);

        webTestClient.post()
                .uri("/api/tacos")
                .body(unsavedTacoMono, Taco.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.name").isEqualTo("New Taco");
    }

    private Taco buildTaco(Integer index){
        Taco taco = new Taco();
        //taco.setId(UUID.randomUUID().toString());
        taco.setName("Taco " + index);
        taco.setIngredients(Set.of(1L));
        return taco;
    }
}