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
public class Donation {
    private Boolean money;
    private Boolean food;
    private Boolean antiFleas;
    private Boolean timeToHelp;
    private Boolean other;
    private String otherDescription;
}
