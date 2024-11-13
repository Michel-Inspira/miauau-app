package com.miauau.platform.requests;

public record OccupationRequest(
        String profession,
        OccupationDetailsRequest occupation,
        String rent
) {
}
