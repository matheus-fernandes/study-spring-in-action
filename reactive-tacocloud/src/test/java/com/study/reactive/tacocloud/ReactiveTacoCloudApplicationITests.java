package com.study.reactive.tacocloud;

import com.study.reactive.tacocloud.config.PostgreContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(PostgreContainerConfig.class)
class ReactiveTacoCloudApplicationITests {

	@Test
	void contextLoads() {
	}

}
