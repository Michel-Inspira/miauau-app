package com.miauau.platform.requests;

import java.time.LocalDate;

public record AnimalRequest(
        // Animal information
        String name,
        String animalType,
        Character sex,
        String details,
        String color,
        Integer age,
        Boolean hasFIV,
        Boolean hasFeLV,
        String rescueDetails,
        String rescueReport,
        // Health status
        String needsCare,
        Boolean healthy,
        Boolean dirty,
        Boolean hurt,
        Boolean mange,
        Boolean fleas,
        Boolean ticks,
        Boolean vomiting,
        Boolean limping,
        String other,
        // Animal conditions
        Boolean isVaccinated,
        LocalDate lastVaccinationDate,
        Boolean isVermifugated,
        LocalDate lastVermifugationDate,
        Boolean antiFleas,
        LocalDate lastAntiFleasDate,
        // Rescuer information
        String rescuerName,
        String rescuerPhone,
        String rescuerDonationType
) {
}
