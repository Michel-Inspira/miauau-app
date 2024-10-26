package com.miauau.platform.responses;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record AnimalResponse(
        UUID id,
        String name,
        Character sex,
        Integer age,
        LocalDateTime created_At,
        Map<String, String> healthStatus,
        Map<String, String> others
) {
}
