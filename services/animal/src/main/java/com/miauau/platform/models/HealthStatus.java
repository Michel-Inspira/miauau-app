package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class HealthStatus {
    private String needsCare;
    private Boolean healthy;
    private Boolean dirty;
    private Boolean hurt;
    private Boolean mange;
    private Boolean fleas;
    private Boolean ticks;
    private Boolean vomiting;
    private Boolean limping;
    private String other;
}
