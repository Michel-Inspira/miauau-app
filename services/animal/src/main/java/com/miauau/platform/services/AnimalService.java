package com.miauau.platform.services;

import com.miauau.platform.exceptions.AnimalNotFoundException;
import com.miauau.platform.models.Animal;
import com.miauau.platform.models.Donation;
import com.miauau.platform.models.HealthStatus;
import com.miauau.platform.models.RescueInfo;
import com.miauau.platform.models.Rescuer;
import com.miauau.platform.repositories.AnimalRepository;
import com.miauau.platform.requests.AnimalRequest;
import com.miauau.platform.requests.DonnationRequest;
import com.miauau.platform.requests.HealthSituationRequest;
import com.miauau.platform.requests.ResponsibleRequest;
import com.miauau.platform.responses.AnimalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository repository;
    private final AnimalMapper mapper;

    public List<AnimalResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public AnimalResponse getById(UUID id) {
        Animal animal = repository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(
                        format("Animal with id %s not found", id))
                );
        return mapper.toResponse(animal);
    }

    public AnimalResponse create(AnimalRequest request) {

        Animal animal = repository.save(mapper.toEntity(request));
        return mapper.toResponse(animal);
    }

    public AnimalResponse update(UUID id, AnimalRequest request) {
        Animal animal = repository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(
                        format("Cannot updated animal: Animal with id %s not found", id))
                );
        requestToEntity(animal, request);
        repository.save(animal);

        return mapper.toResponse(animal);
    }

    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new AnimalNotFoundException(
                    format("Cannot delete animal: Animal with id %s not found", id)
            );
        }
    }

    private void requestToEntity(Animal animal, AnimalRequest request) {
        animal.setName(request.name());
        animal.setAnimalType(request.type());
        animal.setSex(request.sex());
        animal.setPregnant(request.pregnant());
        animal.setCastrated(request.castrated());
        animal.setColor(request.color());
        animal.setAgeGroup(request.ageGroup());
        animal.setApproximateAge(request.approximateAge());
        animal.setHasFIV(request.fiv());
        animal.setHasFeLV(request.felv());
        animal.setNeedsCare(request.needsCare());
        animal.setAntiFleas(request.antiFleas());
        animal.setAntiFleasApplicationDate(request.antiFleasApplicationDate());
        animal.setDewormed(request.dewormed());
        animal.setDewormingDate(request.dewormingDate());
        animal.setVaccinated(request.vaccinated());
        animal.setVaccinationDate(request.vaccinationDate());

        // Map health situation using builder
        HealthSituationRequest healthSituation = request.healthSituation();
        animal.setHealthStatus(HealthStatus.builder()
                .healthy(healthSituation.healthy())
                .dirty(healthSituation.dirty())
                .hurt(healthSituation.hurt())
                .mange(healthSituation.mange())
                .fleas(healthSituation.fleas())
                .ticks(healthSituation.ticks())
                .vomiting(healthSituation.vomiting())
                .limping(healthSituation.limping())
                .other(healthSituation.other())
                .otherDescription(healthSituation.otherDescription())
                .build());

        // Map rescuer information using builders
        ResponsibleRequest responsible = request.rescue().responsible();
        DonnationRequest donnation = responsible.donnation();
        animal.setRescueInfo(RescueInfo.builder()
                .howDidItArrive(request.rescue().howDidItArrive())
                .description(request.rescue().description())
                .rescuer(Rescuer.builder()
                        .name(responsible.name())
                        .phone(responsible.phone())
                        .donation(Donation.builder()
                                .money(donnation.money())
                                .food(donnation.food())
                                .antiFleas(donnation.antiFleas())
                                .timeToHelp(donnation.timeToHelp())
                                .other(donnation.other())
                                .otherDescription(donnation.otherDescription())
                                .build())
                        .build())
                .build());
    }
}

