package com.miauau.platform.responses;

import com.miauau.platform.models.HealthStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record AnimalResponse(
        UUID id,
        String name,
        Character sex,
        Integer age,
        HealthStatus healthStatus,
        LocalDateTime created_At,
        Map<String, Object> others
) {
}
