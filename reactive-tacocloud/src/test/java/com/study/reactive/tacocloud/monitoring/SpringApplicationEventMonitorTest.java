package com.study.reactive.tacocloud.monitoring;

import com.study.reactive.tacocloud.config.PostgreContainerConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(PostgreContainerConfig.class)
class SpringApplicationEventMonitorTest {
    void startApplication(){

    }
}