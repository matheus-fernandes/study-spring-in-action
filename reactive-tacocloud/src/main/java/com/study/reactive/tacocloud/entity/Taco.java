package com.study.reactive.tacocloud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Taco {
    @Id
    private Long id;

    @NonNull
    private String name;

    private Set<Long> ingredients = new HashSet<>();

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient.getId());
    }
}

