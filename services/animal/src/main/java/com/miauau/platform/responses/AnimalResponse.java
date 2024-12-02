package com.miauau.platform.responses;

import com.miauau.platform.requests.HealthSituationRequest;
import com.miauau.platform.requests.RescueRequest;

import java.util.UUID;

public record AnimalResponse(
        UUID id,
        Integer animalNumber,
        String name,
        String imagePath,
        String type,
        String ageGroup,
        String sex,
        String pregnant,
        boolean castrated,
        String color,
        String approximateAge,
        boolean fiv,
        boolean felv,
        HealthSituationRequest healthSituation,
        String needsCare,
        String vaccinated,
        String vaccinationDate,
        String dewormed,
        String dewormingDate,
        String antiFleas,
        String antiFleasApplicationDate,
        RescueRequest rescue,
        boolean isAdopted,
        boolean isAtEvent,
        String ongId

) {
}
