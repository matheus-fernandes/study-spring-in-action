package com.study.reactive.tacocloud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Taco {
    @Id
    private String id;

    @NonNull
    private String name;

    private Set<Long> ingredients;
}
