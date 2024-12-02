package com.miauau.platform.requests;

public record AnimalRequest(
        String name,
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
