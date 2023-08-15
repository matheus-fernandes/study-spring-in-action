package com.study.reactive.tacocloud.controller;

import com.study.reactive.tacocloud.entity.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @GetMapping("/{slug}")
    public Mono<ResponseEntity<Ingredient>> getIngredient(@RequestParam("slug") String slug){
        if ("TMT".equals(slug)){
            return Mono.just(
                    ResponseEntity.ok(
                            new Ingredient(slug, "Tomato", Ingredient.Type.VEGGIES)));
        }

        return Mono.just(
                ResponseEntity
                        .notFound()
                        .header("X_UNAVAILABLE", "true")
                        .build());
    }
}
