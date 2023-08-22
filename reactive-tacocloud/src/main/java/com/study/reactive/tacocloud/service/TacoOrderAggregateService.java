package com.study.reactive.tacocloud.service;

import com.study.reactive.tacocloud.entity.Order;
import com.study.reactive.tacocloud.entity.Taco;
import com.study.reactive.tacocloud.repository.OrderRepository;
import com.study.reactive.tacocloud.repository.TacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TacoOrderAggregateService {
    private final TacoRepository tacoRepository;
    private final OrderRepository orderRepository;

    public Mono<Order> save(Order orderParam){
        return Mono.just(orderParam)
                .flatMap(order -> {
                    List<Taco> tacos = order.getTacos();
                    order.setTacos(new ArrayList<>());
                    return tacoRepository.saveAll(tacos)
                            .map(taco -> {
                                order.addTaco(taco);
                                return order;
                            }).last();
                }).flatMap(orderRepository::save);
    }

    public Mono<Order> findById(Long id){
        return orderRepository
                .findById(id)
                .flatMap(order ->
                        tacoRepository.findAllById(order.getTacoIds())
                            .map(taco -> {
                                order.addTaco(taco);
                                return order;
                            }).last()
                );
    }
}
