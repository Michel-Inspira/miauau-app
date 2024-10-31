package com.miauau.platform.services;

import com.miauau.platform.models.Animal;
import com.miauau.platform.models.AnimalConditions;
import com.miauau.platform.models.Donation;
import com.miauau.platform.models.HealthStatus;
import com.miauau.platform.models.RescueInfo;
import com.miauau.platform.models.Rescuer;
import com.miauau.platform.requests.AnimalRequest;
import com.miauau.platform.requests.DonnationRequest;
import com.miauau.platform.requests.HealthSituationRequest;
import com.miauau.platform.requests.RescueRequest;
import com.miauau.platform.requests.ResponsibleRequest;
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
                .animalType(request.type())
                .sex(request.sex())
                .pregnant(request.pregnant())
                .castrated(request.castrated())
                .color(request.color())
                .ageGroup(request.ageGroup())
                .approximateAge(request.approximateAge())
                .hasFIV(request.fiv())
                .hasFeLV(request.felv())
                .healthStatus(HealthStatus.builder()
                        .healthy(request.healthSituation().healthy())
                        .dirty(request.healthSituation().dirty())
                        .hurt(request.healthSituation().hurt())
                        .mange(request.healthSituation().mange())
                        .fleas(request.healthSituation().fleas())
                        .ticks(request.healthSituation().ticks())
                        .vomiting(request.healthSituation().vomiting())
                        .limping(request.healthSituation().limping())
                        .other(request.healthSituation().other())
                        .otherDescription(request.healthSituation().otherDescription())
                        .build())
                .animalConditions(AnimalConditions.builder()
                        .isVaccinated(request.vaccinated())
                        .lastVaccinationDate(request.vaccinationDate())
                        .isVermifugated(request.dewormed())
                        .lastVermifugationDate(request.dewormingDate())
                        .antiFleas(request.antiFleas())
                        .lastAntiFleasDate(request.antiFleasApplicationDate())
                        .build())
                .rescueInfo(RescueInfo.builder()
                        .howDidItArrive(request.rescue().howDidItArrive())
                        .description(request.rescue().description())
                        .rescuer(Rescuer.builder()
                                .name(request.rescue().responsible().name())
                                .phone(request.rescue().responsible().phone())
                                .donation(Donation.builder()
                                        .money(request.rescue().responsible().donnation().money())
                                        .food(request.rescue().responsible().donnation().food())
                                        .antiFleas(request.rescue().responsible().donnation().antiFleas())
                                        .timeToHelp(request.rescue().responsible().donnation().timeToHelp())
                                        .other(request.rescue().responsible().donnation().other())
                                        .otherDescription(request.rescue().responsible().donnation().otherDescription())
                                        .build())
                                .build())
                        .build())
                .build();
    }

    public AnimalResponse toResponse(Animal animal) {
        if (animal == null) {
            return null;
        }
        return new AnimalResponse(
                animal.getId(), // Assuming AnimalResponse expects an ID
                animal.getName(),
                animal.getImagePath(),
                animal.getAnimalType(),
                animal.getAgeGroup(),
                animal.getSex(),
                animal.getPregnant(),
                animal.getCastrated(),
                animal.getColor(),
                animal.getApproximateAge(),
                animal.getHasFIV(),
                animal.getHasFeLV(),
                new HealthSituationRequest(
                        animal.getHealthStatus().getHealthy(),
                        animal.getHealthStatus().getDirty(),
                        animal.getHealthStatus().getHurt(),
                        animal.getHealthStatus().getMange(),
                        animal.getHealthStatus().getFleas(),
                        animal.getHealthStatus().getTicks(),
                        animal.getHealthStatus().getVomiting(),
                        animal.getHealthStatus().getLimping(),
                        animal.getHealthStatus().getOther(),
                        animal.getHealthStatus().getOtherDescription()
                ),
                animal.getAnimalConditions().getIsVaccinated(),
                animal.getAnimalConditions().getLastVaccinationDate(),
                animal.getAnimalConditions().getIsVermifugated(),
                animal.getAnimalConditions().getLastVermifugationDate(),
                animal.getAnimalConditions().getAntiFleas(),
                animal.getAnimalConditions().getLastAntiFleasDate(),
                new RescueRequest(
                        animal.getRescueInfo().getHowDidItArrive(),
                        animal.getRescueInfo().getDescription(),
                        new ResponsibleRequest(
                                animal.getRescueInfo().getRescuer().getName(),
                                animal.getRescueInfo().getRescuer().getPhone(),
                                new DonnationRequest(
                                        animal.getRescueInfo().getRescuer().getDonation().getMoney(),
                                        animal.getRescueInfo().getRescuer().getDonation().getFood(),
                                        animal.getRescueInfo().getRescuer().getDonation().getAntiFleas(),
                                        animal.getRescueInfo().getRescuer().getDonation().getTimeToHelp(),
                                        animal.getRescueInfo().getRescuer().getDonation().getOther(),
                                        animal.getRescueInfo().getRescuer().getDonation().getOtherDescription()
                                )
                        )
                )
        );
    }
}