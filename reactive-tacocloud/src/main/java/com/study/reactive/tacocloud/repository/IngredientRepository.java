package com.study.reactive.tacocloud.repository;

import com.study.reactive.tacocloud.entity.Ingredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, Long> {
    Mono<Ingredient> findBySlug(String slug);
}
