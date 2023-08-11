package com.study.reactive.tacocloud.controller;

import com.study.reactive.tacocloud.entity.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Ingredient>> getIngredient(@RequestParam("id") String id){
        if ("TMT".equals(id)){
            return Mono.just(
                    ResponseEntity.ok(
                            new Ingredient(id, "Tomato", Ingredient.Type.VEGGIES)));
        }

        return Mono.just(
                ResponseEntity
                        .notFound()
                        .header("X_UNAVAILABLE", "true")
                        .build());
    }
}
