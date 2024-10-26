package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class HealthStatus {
    private Map<String, Object> health;
}
