package com.miauau.platform.services;

import com.miauau.platform.models.Animal;
import com.miauau.platform.models.HealthStatus;
import com.miauau.platform.requests.AnimalRequest;
import com.miauau.platform.responses.AnimalResponse;
import org.springframework.stereotype.Service;

@Service
public class AnimalMapper {
    public Animal toEntity(AnimalRequest request) {
        if (request == null) {
            return null;
        }
        return Animal.builder()
                .name(request.name())
                .sex(request.sex())
                .age(request.age())
                .healthStatus(HealthStatus.builder()
                        .healthStatus(request.healthStatus())
                        .build())
                .others(request.others())
                .build();
    }

    public AnimalResponse toResponse(Animal animal) {
        return new AnimalResponse(
                animal.getId(),
                animal.getName(),
                animal.getSex(),
                animal.getAge(),
                animal.getCreatedAt(),
                animal.getHealthStatus().getHealthStatus(),
                animal.getOthers()
        );
    }
}
