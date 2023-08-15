package com.study.reactive.tacocloud.controller;

import com.study.reactive.tacocloud.entity.Taco;
import com.study.reactive.tacocloud.repository.TacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/tacos")
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
public class TacoController {
    private final TacoRepository tacoRepository;

    @GetMapping(params = "recent")
    public Flux<Taco> recentTacos(){
        return tacoRepository.findAll().take(12);
    }

    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") Long id){
        return tacoRepository.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> createTaco(@RequestBody Mono<Taco> taco){
        return taco
                .flatMap(tacoRepository::save);
    }
}
