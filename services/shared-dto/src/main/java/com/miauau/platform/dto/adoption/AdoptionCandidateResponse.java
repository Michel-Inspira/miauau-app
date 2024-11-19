package com.miauau.platform.dto.adoption;

public record AdoptionCandidateResponse(
        String id,
        String name,
        String age,
        String occupation,
        String livingSituation
) {
}
