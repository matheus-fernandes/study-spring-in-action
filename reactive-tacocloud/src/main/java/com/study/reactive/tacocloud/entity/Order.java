package com.study.reactive.tacocloud.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.LinkedHashSet;
import java.util.Set;


@Data
@Table(name = "ORDERS")
public class Order{
    @Id
    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private Set<Long> tacos = new LinkedHashSet<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco.getId());
    }
}
