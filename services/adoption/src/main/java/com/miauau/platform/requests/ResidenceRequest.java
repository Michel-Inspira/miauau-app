package com.miauau.platform.requests;

public record ResidenceRequest(
        String type,
        String otherDescription,
        String situation
) {
}
