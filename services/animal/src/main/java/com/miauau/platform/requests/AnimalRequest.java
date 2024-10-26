package com.miauau.platform.requests;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record AnimalRequest(
        @NotNull(message = "Animal name is required")
        String name,
        @NotNull(message = "Animal sex is required")
        Character sex,
        Integer age,
        Map<String, String> healthStatus,
        Map<String, String> others
) {
}
