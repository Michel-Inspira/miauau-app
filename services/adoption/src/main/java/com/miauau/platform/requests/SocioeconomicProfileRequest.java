package com.miauau.platform.requests;

public record SocioeconomicProfileRequest(
        OccupationRequest occupation,
        ResidenceRequest residence
) {
}
