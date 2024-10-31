package com.miauau.platform.requests;

public record HealthSituationRequest(
        boolean healthy,
        boolean dirty,
        boolean hurt,
        boolean mange,
        boolean fleas,
        boolean ticks,
        boolean vomiting,
        boolean limping,
        boolean other,
        String otherDescription
) {
}