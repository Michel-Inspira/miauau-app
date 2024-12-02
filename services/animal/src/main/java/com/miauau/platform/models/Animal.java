package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Animal {

    @Id
    private UUID id;
    private Integer animalNumber;
    private String name;
    private String imagePath;
    private String animalType;
    private String ageGroup;
    private String sex;
    private String pregnant;
    private Boolean castrated;
    private String color;
    private String approximateAge;
    private Boolean hasFIV;
    private Boolean hasFeLV;
    private HealthStatus healthStatus;
    private String needsCare;
    private String vaccinated;
    private String vaccinationDate;
    private String dewormed;
    private String dewormingDate;
    private String antiFleas;
    private String antiFleasApplicationDate;
    private RescueInfo rescueInfo;
    private boolean isAdopted = false;
    private boolean isAtEvent;
    private String ongId;
    @CreatedDate
    private LocalDateTime createdAt;

}
