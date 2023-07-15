package com.study.reactive.tacocloud.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@TestConfiguration
public class PostgreContainerConfig {

    public static final String DOCKER_IMAGE_NAME = "postgres:15-alpine";
    public static final String DRIVER_NAME = "postgresql";

    @Bean
    public PostgreSQLContainer<?> postgreSQLContainer(){
           PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME);
           container.start();
           return container;
    }

    @Bean
    public ConnectionFactory dataSource(PostgreSQLContainer<?> container){
        return ConnectionFactories.get(builder()
                .option(DRIVER, DRIVER_NAME)
                .option(HOST, container.getHost())
                .option(PORT, container.getFirstMappedPort())  // optional, defaults to 5432
                .option(USER, container.getUsername())
                .option(PASSWORD, container.getPassword())
                .build());
    }
}
