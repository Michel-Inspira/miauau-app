package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class AnimalConditions {
    private String isVaccinated;
    private String lastVaccinationDate;
    private String isVermifugated;
    private String lastVermifugationDate;
    private String antiFleas;
    private String lastAntiFleasDate;
}
