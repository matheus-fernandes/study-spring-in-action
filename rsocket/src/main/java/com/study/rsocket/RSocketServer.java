package com.study.rsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Controller
public class RSocketServer {
    private final Logger log = LoggerFactory.getLogger(RSocketServer.class);

    private final EmergencyService emergencyService;

    public RSocketServer(EmergencyService emergencyService){
        this.emergencyService = emergencyService;
    }

    @MessageMapping("greeting/{name}")
    public Mono<String> handleGreeting(@DestinationVariable("name") String name, Mono<String> greeting){
        return greeting
                .doOnNext(g -> log.info("Received a greeting from {}: {}", name, g))
                .map(g -> {
                    return "Hello to you, too, " + name;
                });
    }

    @MessageMapping("stock/{symbol}")
    public Flux<StockQuote> stockQuoteFlux(@DestinationVariable("symbol") String symbol){
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> {
                    BigDecimal price = BigDecimal.valueOf(Math.random());
                    return new StockQuote(symbol, price, Instant.now());
                });
    }

    @MessageMapping("alert")
    public Mono<Void> receiveAlert(Mono<Alert> alertMono){
        return alertMono
                .doOnNext(emergencyService::escalateAlert)
                .thenEmpty(Mono.empty());
    }

    @MessageMapping("gratuity")
    public Flux<Gratuity> calculate(Flux<Gratuity> gratuityInFlux){
        return gratuityInFlux
                .doOnNext(in -> log.info("Calculating gratuity: {}", in))
                .map(in -> {
                    double percentAsDecimal = in.percent() / 100.0D;
                    BigDecimal gratuity = in.billTotal().multiply(BigDecimal.valueOf(percentAsDecimal));
                    return new Gratuity(in.billTotal(), in.percent(), gratuity);
                });
    }
}
