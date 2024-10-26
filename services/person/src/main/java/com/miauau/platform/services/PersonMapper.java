package com.miauau.platform.services;

import com.miauau.platform.models.Address;
import com.miauau.platform.models.Person;
import com.miauau.platform.requests.PersonRequest;
import com.miauau.platform.responses.PersonResponse;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
    public PersonResponse toResponse(Person person) {
        if (person == null) {
            return null;
        }
        return new PersonResponse(
                person.getId(),
                person.getName(),
                person.getIsVolunteer(),
                person.getRole(),
                person.getEmail(),
                person.getPhone(),
                person.getAddress().getZipCode(),
                person.getAddress().getStreet(),
                person.getAddress().getNumber(),
                person.getAddress().getComplement(),
                person.getAddress().getNeighborhood()
        );
    }

    public Person toEntity(PersonRequest request) {
        return Person.builder()
                .name(request.name())
                .isVolunteer(request.isVolunteer())
                .role(request.role())
                .email(request.email())
                .phone(request.phone())
                .address(Address.builder()
                        .zipCode(request.zipCode())
                        .street(request.street())
                        .number(request.number())
                        .complement(request.complement())
                        .neighborhood(request.neighborhood())
                        .build())
                .build();
    }
}
