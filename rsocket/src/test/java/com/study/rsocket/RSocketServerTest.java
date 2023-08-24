package com.study.rsocket;

import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class RSocketServerTest {
    private static RSocketRequester requester;

    @SpyBean
    private EmergencyService emergencyService;

    @BeforeAll
    public static void setupOnce(@Autowired RSocketRequester.Builder builder,
                                 @LocalRSocketServerPort Integer port,
                                 @Autowired RSocketStrategies rSocketStrategies){
        requester = builder.tcp("127.0.0.1", port);
    }

    @Test
    void testGreeting(){
        Mono<String> responseMono = requester.route("greeting/Matheus")
                .data(Mono.just("Hello RSocket server!"))
                .retrieveMono(String.class);

        Optional<String> responseOptional = responseMono
                .doOnError(throwable -> Assertions.fail(throwable.getLocalizedMessage()))
                .delaySubscription(Duration.ofSeconds(1))
                .blockOptional();

        assertThat(responseOptional)
                .isPresent()
                .hasValue("Hello to you, too, Matheus");
    }

    @Test
    void testStockQuoteFlux(){
        Flux<StockQuote> responseMono = requester.route("stock/RUT")
                .retrieveFlux(StockQuote.class)
                .delaySubscription(Duration.ofSeconds(1))
                .log();

        List<StockQuote> stockQuotes = responseMono
                .take(10)
                .collect(Collectors.toList())
                .block();

        assertThat(stockQuotes)
                .hasSize(10)
                .first()
                .matches(qt -> "RUT".equals(qt.symbol()));
    }

    @Test
    void testAlert(){
        Alert alert = new Alert(Alert.Level.RED, "Matheus", Instant.now());

        Mono<Void> response = requester.route("alert")
                .data(Mono.just(alert))
                .retrieveMono(Void.class)
                .delaySubscription(Duration.ofSeconds(1));

        response.block();
        verify(emergencyService).escalateAlert(alert);
    }

    @Test
    void testGratuity(){
        Flux<Gratuity> gratuityFlux =
                Flux.fromArray(new Gratuity[]{
                        new Gratuity(BigDecimal.valueOf(35.50), 18, null),
                        new Gratuity(BigDecimal.valueOf(10.00), 10, null),
                        new Gratuity(BigDecimal.valueOf(23.25), 20, null)
                })
                .delayElements(Duration.ofMillis(300));

        Flux<Gratuity> response = requester.route("gratuity")
                .data(gratuityFlux)
                .retrieveFlux(Gratuity.class)
                .delaySubscription(Duration.ofSeconds(1));

        List<Gratuity> gratuities = response
                .collect(Collectors.toList())
                .block();

        assertThat(gratuities)
                .hasSize(3)
                .element(1)
                .matches(g -> BigDecimal.valueOf(1).compareTo(g.gratuity()) == 0);
    }
}
