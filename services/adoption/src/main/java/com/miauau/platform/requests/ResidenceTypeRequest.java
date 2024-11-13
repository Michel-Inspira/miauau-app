package com.miauau.platform.requests;

public record ResidenceTypeRequest(
        boolean house,
        boolean apartment,
        boolean grange,
        boolean other,
        String otherDescription
) {
}
