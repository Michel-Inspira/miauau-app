package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class CandidateForm {

    @Id
    private String id = UUID.randomUUID().toString();
    private String personId;
    private Map<String, Object> occupation;
    private Map<String, Object> residence;
    private Map<String, Object> housingDetails;
    private Map<String, Object> coexistence;
    private Map<String, Object> previousAnimals;
    private String adoptionMotivation;
    private String adoptionMotivationDescription;
    private Map<String, Map<String, Boolean>> animalsOfInterest;
    private Map<String, Object> dailyCare;
    private Map<String, Object> attitudesTowardsTheAnimal;
    private Map<String, Boolean> agreements;
}
