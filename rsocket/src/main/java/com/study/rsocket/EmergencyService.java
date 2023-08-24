package com.study.rsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import static com.study.rsocket.Alert.*;

@Component
public class EmergencyService {
    private static final Logger log = LoggerFactory.getLogger(EmergencyService.class);
    public void escalateAlert(Alert alert){
        switch (alert.level()){
            case BLACK -> log.error(alert.toString());
            case RED -> log.warn(alert.toString());
            default -> log.info(alert.toString());
        }
    }
}
