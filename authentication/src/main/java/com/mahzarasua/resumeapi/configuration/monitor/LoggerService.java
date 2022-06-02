package com.mahzarasua.resumeapi.configuration.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class LoggerService implements HealthIndicator {
    private final String LOGGER_SERVICE = "LoggerService";

    @Override
    public Health health() {
        if(isLoggerService())
            return Health.up().withDetail(LOGGER_SERVICE, "Service is running").build();
        return Health.down().withDetail(LOGGER_SERVICE, "Service is down").build();
    }

    private boolean isLoggerService(){
        //logic
        return true;
    }
}
