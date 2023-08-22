package com.study.reactive.tacocloud.service;

import com.study.reactive.tacocloud.config.PostgreContainerConfig;
import com.study.reactive.tacocloud.entity.Order;
import com.study.reactive.tacocloud.entity.Taco;
import com.study.reactive.tacocloud.repository.OrderRepository;
import com.study.reactive.tacocloud.repository.TacoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@DirtiesContext
@Import(PostgreContainerConfig.class)
class TacoOrderAggregateServiceTest {

    @Autowired
    TacoRepository tacoRepository;

    @Autowired
    OrderRepository orderRepository;

    TacoOrderAggregateService service;

    @BeforeEach
    void setup(){
        service = new TacoOrderAggregateService(tacoRepository, orderRepository);
        tacoRepository.deleteAll().subscribe();
    }

    @Test
    void shouldSaveAndFetchOrders() {
        Order newOrder = new Order();
        newOrder.setDeliveryName("Test Customer");
        newOrder.setDeliveryStreet("1234 North Street");
        newOrder.setDeliveryCity("Notrees");
        newOrder.setDeliveryState("TX");
        newOrder.setDeliveryZip("79759");
        newOrder.setCcNumber("4111111111111");
        newOrder.setCcExpiration("12/24");
        newOrder.setCcCVV("123");

        newOrder.addTaco(new Taco("Test Taco One"));
        newOrder.addTaco(new Taco("Test Taco Two"));

        StepVerifier.create(service.save(newOrder))
                .assertNext(this::assertOrder)
                .verifyComplete();

        StepVerifier.create(service.findById(1L))
                .assertNext(this::assertOrder)
                .verifyComplete();
    }

    private void assertOrder(Order order) {
        assertThat(order.getId()).isEqualTo(1L);
        assertThat(order.getDeliveryName()).isEqualTo("Test Customer");
        assertThat(order.getTacoIds()).hasSize(2);

        assertThat(order.getTacos())
                .first()
                .matches(taco -> taco.getId().equals(1L))
                .matches(taco -> taco.getName().equals("Test Taco One"));

        assertThat(order.getTacos())
                .element(1)
                .matches(taco -> taco.getId().equals(2L))
                .matches(taco -> taco.getName().equals("Test Taco Two"));
    }

}