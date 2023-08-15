package com.study.reactive.tacocloud.repository;

import com.study.reactive.tacocloud.entity.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}
