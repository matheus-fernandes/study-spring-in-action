package com.study.reactive.tacocloud.repository;

import com.study.reactive.tacocloud.config.PostgreContainerConfig;
import com.study.reactive.tacocloud.entity.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataR2dbcTest
@Import(PostgreContainerConfig.class)
class IngredientRepositoryITest {
    @Autowired
    IngredientRepository ingredientRepository;

    @BeforeEach
    void setup(){
        Flux<Ingredient> deleteAndInsert = ingredientRepository.deleteAll()
                .thenMany(ingredientRepository.saveAll(
                        Flux.just(
                                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                                new Ingredient("CHED", "Cheddar Cheese", Ingredient.Type.CHEESE)
                        )
                ));

        StepVerifier.create(deleteAndInsert)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void shouldSaveAndFetchIngredients(){
        StepVerifier.create(ingredientRepository.findAll())
                .recordWith(ArrayList::new)
                .thenConsumeWhile(i -> true)
                .consumeRecordedWith(ingredients -> {
                    assertThat(ingredients).hasSize(3);
                    assertThat(ingredients).contains(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
                    assertThat(ingredients).contains(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
                    assertThat(ingredients).contains(new Ingredient("CHED", "Cheddar Cheese", Ingredient.Type.CHEESE));
                })
                .verifyComplete();
    }

    @Test
    void shouldSaveAndFetchIngredientBySlug(){
        StepVerifier.create(ingredientRepository.findBySlug("FLTO"))
                .assertNext(ingredient -> assertEquals(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP), ingredient))
                .verifyComplete();
    }
}
