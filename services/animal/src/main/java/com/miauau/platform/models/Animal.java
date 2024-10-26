package com.miauau.platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;
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
    private Character sex;
    private Integer age;
    private HealthStatus healthStatus;
    private LocalDateTime createdAt;
    private Map<String, String> others;
}
