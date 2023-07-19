package com.study.reactive.tacocloud.repository;

import com.study.reactive.tacocloud.config.PostgreContainerConfig;
import com.study.reactive.tacocloud.entity.Taco;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Import(PostgreContainerConfig.class)
class TacoRepositoryITest {
    @Autowired
    TacoRepository repository;

    @Test
    void test(){
        String id = "TEST_ID";
        String name = "My Taco";

        tacoMono(id, name)
                .flatMap(repository::save);

        StepVerifier.create(repository.findById(id))
                .expectNextMatches(t -> name.equals(t.getName()));
    }

    Mono<Taco> tacoMono(String id, String name){
        Taco taco = new Taco(id, "My Taco");
        return Mono.just(taco);
    }
}
