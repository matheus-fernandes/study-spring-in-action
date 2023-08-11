package com.study.reactive.tacocloud.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type{
        VEGGIES
    }
}
