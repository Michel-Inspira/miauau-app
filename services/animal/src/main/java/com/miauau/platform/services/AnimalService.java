package com.miauau.platform.services;

import com.miauau.platform.exceptions.AnimalNotFoundException;
import com.miauau.platform.models.Animal;
import com.miauau.platform.models.AnimalConditions;
import com.miauau.platform.models.HealthStatus;
import com.miauau.platform.models.RescuerInfo;
import com.miauau.platform.repositories.AnimalRepository;
import com.miauau.platform.requests.AnimalRequest;
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
        animal.setAnimalType(request.animalType());
        animal.setSex(request.sex());
        animal.setDetails(request.details());
        animal.setColor(request.color());
        animal.setAge(request.age());
        animal.setHasFIV(request.hasFIV());
        animal.setHasFeLV(request.hasFeLV());
        animal.setRescueDetails(request.rescueDetails());
        animal.setRescueReport(request.rescueReport());
        animal.setHealthStatus(HealthStatus.builder()
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
                .build());
        animal.setAnimalConditions(AnimalConditions.builder()
                .isVaccinated(request.isVaccinated())
                .lastVaccinationDate(request.lastVaccinationDate())
                .isVermifugated(request.isVermifugated())
                .lastVermifugationDate(request.lastVermifugationDate())
                .antiFleas(request.antiFleas())
                .lastAntiFleasDate(request.lastAntiFleasDate())
                .build());
        animal.setRescuerInfo(RescuerInfo.builder()
                .name(request.rescuerName())
                .phone(request.rescuerPhone())
                .donationType(request.rescuerDonationType())
                .build());
    }
}

