package com.miauau.platform.dto.adoption;

import java.util.Map;

public record DetailedAdoptionCandidateResponse(
        Map<String, Object> personalInformation,
        Map<String, Object> socioeconomicProfile,
        Map<String, Object> housingDetails,
        Map<String, Object> coexistence,
        Map<String, Object> animals,
        Map<String, Object> interest,
        Map<String, Object> dailyCare,
        Map<String, Object> attitudesTowardsTheAnimal,
        Map<String, Boolean> agreements
) {
}
