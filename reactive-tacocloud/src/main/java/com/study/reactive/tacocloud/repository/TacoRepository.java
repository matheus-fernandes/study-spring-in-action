package com.study.reactive.tacocloud.repository;

import com.study.reactive.tacocloud.entity.Taco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends ReactiveCrudRepository<Taco, String> {
}
