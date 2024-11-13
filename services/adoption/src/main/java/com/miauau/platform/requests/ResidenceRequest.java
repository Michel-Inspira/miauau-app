package com.miauau.platform.requests;

public record ResidenceRequest(
        ResidenceTypeRequest type,
        boolean own,
        boolean rent,
        boolean inherited
) {
}
