package com.miauau.platform.requests;

import com.miauau.platform.models.HealthStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Map;

public record AnimalRequest(
        @NotNull(message = "Animal name is required")
        String name,
        @NotNull(message = "Animal sex is required")
        Character sex,
        Integer age,
        HealthStatus healthStatus,
        LocalDateTime created_At,
        Map<String, Object> others
) {
}
