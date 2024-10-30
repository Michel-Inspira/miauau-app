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
    private String name;
    private String imagePath;
    private String animalType;
    private Character sex;
    private String details;
    private String color;
    private Integer age;
    private Boolean hasFIV;
    private Boolean hasFeLV;
    private String rescueDetails;
    private String rescueReport;
    private HealthStatus healthStatus;
    private AnimalConditions animalConditions;
    private RescuerInfo rescuerInfo;
    @CreatedDate
    private LocalDateTime createdAt;

}
