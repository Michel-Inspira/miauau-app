package com.miauau.platform.requests;

public record DailyCareRequest(
        String responsibleForCare,
        String responsibleForCareInCaseOfTravel,
        String howWillEducate,
        boolean hasPetCarrier,
        int dailyWalks,
        String timeAlone,
        FoodTypeRequest foodType
) {
}
