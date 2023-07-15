package com.study.reactive.tacocloud.config;

import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

@DataR2dbcTest
@Import(PostgreContainerConfig.class)
public class DataJpaTestContextConfig {
}
