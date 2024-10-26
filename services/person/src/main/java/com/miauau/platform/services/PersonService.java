package com.miauau.platform.services;

import com.miauau.platform.exception.PersonNotFoundException;
import com.miauau.platform.models.Address;
import com.miauau.platform.models.Person;
import com.miauau.platform.repositories.PersonRepository;
import com.miauau.platform.requests.PersonRequest;
import com.miauau.platform.responses.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    public List<PersonResponse> getList() {
        List<Person> entities = repository.findByIsVolunteerFalse();
        return entities.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PersonResponse> getVolunteers() {
        List<Person> entities = repository.findByIsVolunteerTrue();

        return entities.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public PersonResponse getById(UUID id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(
                        format("Person with id %s not found", id))
                );
        return mapper.toResponse(entity);
    }

    public PersonResponse create(PersonRequest request) {
        Person entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public PersonResponse update(UUID id, PersonRequest request) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(
                        format("Cannot updated person: Person with id %s not found", id))
                );
        mergePerson(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PersonNotFoundException(
                    format("Cannot delete person: Person with id %s not found", id)
            );
        }
    }

    private void mergePerson(Person entity, PersonRequest request) {
        entity.setName(request.name());
        entity.setIsVolunteer(request.isVolunteer());
        entity.setRole(request.role());
        entity.setEmail(request.email());
        entity.setPhone(request.phone());
        entity.setAddress(Address.builder()
                .zipCode(request.zipCode())
                .street(request.street())
                .number(request.number())
                .complement(request.complement())
                .neighborhood(request.neighborhood())
                .build());
    }
}
