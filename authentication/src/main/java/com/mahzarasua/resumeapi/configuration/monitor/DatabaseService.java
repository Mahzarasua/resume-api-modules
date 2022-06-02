package com.mahzarasua.resumeapi.configuration.monitor;


import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Component
public class DatabaseService implements HealthIndicator {
    private final String DATABASE_SERVICE = "DatabaseService";

    @Override
    public Health health() {
        if (isDatabaseHealth())
            return Health.up().withDetails(getDetails()).build();
        return Health.down().withDetail(DATABASE_SERVICE, "Service is down").build();
    }

    @NotNull
    private HashMap getDetails() {
        HashMap map = new HashMap();
        map.put(DATABASE_SERVICE, "Service is up");
        map.put("Status", "testing");
        return map;
    }

    private boolean isDatabaseHealth() {
        //logic
        return true;
    }
}
