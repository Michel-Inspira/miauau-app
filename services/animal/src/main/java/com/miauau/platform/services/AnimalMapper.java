package com.miauau.platform.services;

import com.miauau.platform.models.Animal;
import com.miauau.platform.models.AnimalConditions;
import com.miauau.platform.models.HealthStatus;
import com.miauau.platform.models.RescuerInfo;
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
                .animalType(request.animalType())
                .sex(request.sex())
                .details(request.details())
                .color(request.color())
                .age(request.age())
                .hasFIV(request.hasFIV())
                .hasFeLV(request.hasFeLV())
                .rescueDetails(request.rescueDetails())
                .rescueReport(request.rescueReport())
                .healthStatus(HealthStatus.builder()
                        .needsCare(request.needsCare())
                        .healthy(request.healthy())
                        .dirty(request.dirty())
                        .hurt(request.hurt())
                        .mange(request.mange())
                        .fleas(request.fleas())
                        .ticks(request.ticks())
                        .vomiting(request.vomiting())
                        .limping(request.limping())
                        .other(request.other())
                        .build())
                .animalConditions(AnimalConditions.builder()
                        .isVaccinated(request.isVaccinated())
                        .lastVaccinationDate(request.lastVaccinationDate())
                        .isVermifugated(request.isVermifugated())
                        .lastVermifugationDate(request.lastVermifugationDate())
                        .antiFleas(request.antiFleas())
                        .lastAntiFleasDate(request.lastAntiFleasDate())
                        .build())
                .rescuerInfo(RescuerInfo.builder()
                        .name(request.rescuerName())
                        .phone(request.rescuerPhone())
                        .donationType(request.rescuerDonationType())
                        .build())
                .build();
    }

    public AnimalResponse toResponse(Animal animal) {
        return new AnimalResponse(
                animal.getId(),
                animal.getName(),
                animal.getImagePath(),
                animal.getAnimalType(),
                animal.getSex(),
                animal.getDetails(),
                animal.getColor(),
                animal.getAge(),
                animal.getHasFIV(),
                animal.getHasFeLV(),
                animal.getRescueDetails(),
                animal.getRescueReport(),
                animal.getHealthStatus().getNeedsCare(),
                animal.getHealthStatus().getHealthy(),
                animal.getHealthStatus().getDirty(),
                animal.getHealthStatus().getHurt(),
                animal.getHealthStatus().getMange(),
                animal.getHealthStatus().getFleas(),
                animal.getHealthStatus().getTicks(),
                animal.getHealthStatus().getVomiting(),
                animal.getHealthStatus().getLimping(),
                animal.getHealthStatus().getOther(),
                animal.getAnimalConditions().getIsVaccinated(),
                animal.getAnimalConditions().getLastVaccinationDate(),
                animal.getAnimalConditions().getIsVermifugated(),
                animal.getAnimalConditions().getLastVermifugationDate(),
                animal.getAnimalConditions().getAntiFleas(),
                animal.getAnimalConditions().getLastAntiFleasDate(),
                animal.getRescuerInfo().getName(),
                animal.getRescuerInfo().getPhone(),
                animal.getRescuerInfo().getDonationType()
        );
    }
}
