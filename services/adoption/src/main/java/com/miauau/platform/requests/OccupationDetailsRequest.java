package com.miauau.platform.requests;

public record OccupationDetailsRequest(
        boolean working,
        boolean studying,
        boolean unemployed,
        boolean other,
        String otherDescription
) {
}
