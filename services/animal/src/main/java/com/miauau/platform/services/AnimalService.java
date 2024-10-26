package com.miauau.platform.services;

import com.miauau.platform.exceptions.AnimalNotFoundException;
import com.miauau.platform.models.Animal;
import com.miauau.platform.repositories.AnimalRepository;
import com.miauau.platform.requests.AnimalRequest;
import com.miauau.platform.responses.AnimalResponse;
import io.micrometer.common.util.StringUtils;
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
        mergeAnimal(animal, request);
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

    private void mergeAnimal(Animal animal, AnimalRequest request) {
        if (StringUtils.isNotBlank(request.name())) {
            animal.setName(request.name());
        }
        if (request.sex() != null) {
            animal.setSex(request.sex());
        }
        if (request.age() != null) {
            animal.setAge(request.age());
        }
        if (request.healthStatus() != null) {
            animal.setHealthStatus(request.healthStatus());
        }
        if (request.others() != null) {
            animal.setOthers(request.others());
        }
    }
}

