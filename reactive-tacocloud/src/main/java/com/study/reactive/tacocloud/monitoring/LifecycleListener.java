package com.study.reactive.tacocloud.monitoring;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;

@Slf4j
@NoArgsConstructor
public class LifecycleListener implements ApplicationListener<SpringApplicationEvent> {
    private static final String LIFECYCLE_EVENT_LOGGING = "LifecycleEvent={}";

    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        String eventLabel = event.getClass().getSimpleName();
        log.info(LIFECYCLE_EVENT_LOGGING, eventLabel);
    }
}
