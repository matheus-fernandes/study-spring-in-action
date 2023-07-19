package com.study.reactive.tacocloud.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.boot.r2dbc.*;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Slf4j
@TestConfiguration
public class PostgreContainerConfig {

    public static final String DOCKER_IMAGE_NAME = "postgres:15-alpine";
    public static final String DRIVER_NAME = "postgresql";
    public static final String INIT_SCRIPT_PATH = "script.sql";

    @Bean
    public PostgreSQLContainer<?> postgreSQLContainer(){
           PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME);
           container.withInitScript(INIT_SCRIPT_PATH);
           container.withLogConsumer(out -> log.debug(out.getUtf8String()));
           container.start();
           return container;
    }

    @Bean
    public ConnectionFactory dataSource(PostgreSQLContainer<?> container){
        return ConnectionFactoryBuilder.withOptions(builder()
                .option(DRIVER, DRIVER_NAME)
                .option(HOST, container.getHost())
                .option(PORT, container.getFirstMappedPort())  // optional, defaults to 5432
                .option(USER, container.getUsername())
                .option(DATABASE, container.getDatabaseName())
                .option(PASSWORD, container.getPassword()))
                .build();
    }
}
