package com.study.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class MergeFluxTest {
    @Test
    void mergeFluxes(){
        Flux<String> characterFlux = Flux
                .just("Garfield", "Kojak", "Barbosa");
    }
}
