package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class AnimalConditions {
    private Boolean isVaccinated;
    private LocalDate lastVaccinationDate;
    private Boolean isVermifugated;
    private LocalDate lastVermifugationDate;
    private Boolean antiFleas;
    private LocalDate lastAntiFleasDate;
}
