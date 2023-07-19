package com.study.reactive.tacocloud;

import com.study.reactive.tacocloud.monitoring.LifecycleListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class ReactiveTacoCloudApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(ReactiveTacoCloudApplication.class)
				.listeners(new LifecycleListener())
				.run(args);
	}

}
