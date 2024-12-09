package com.miauau.platform.services;

import com.miauau.platform.dto.person.PersonResponse;
import com.miauau.platform.models.Address;
import com.miauau.platform.models.Person;
import com.miauau.platform.requests.PersonRequest;
import com.miauau.platform.requests.VolunteerRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

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
            person.getCpf(),
            person.getRg(),
            person.getBirthDate(),
            person.getLandline(),
            Optional.ofNullable(person.getAddress()).map(Address::getZipCode).orElse(null),
            Optional.ofNullable(person.getAddress()).map(Address::getStreet).orElse(null),
            Optional.ofNullable(person.getAddress()).map(Address::getNumber).orElse(null),
            Optional.ofNullable(person.getAddress()).map(Address::getComplement).orElse(null),
            Optional.ofNullable(person.getAddress()).map(Address::getNeighborhood).orElse(null)
        );
    }

    public Person toEntity(PersonRequest request) {
        return Person.builder()
                .name(request.name())
                .isVolunteer(request.isVolunteer())
                .role(request.role())
                .email(request.email())
                .phone(request.phone())
                .cpf(request.cpf())
                .rg(request.rg())
                .birthDate(request.birthDate())
                .landline(request.landline())
                .address(Address.builder()
                        .zipCode(request.zipCode())
                        .street(request.street())
                        .number(request.number())
                        .complement(request.complement())
                        .neighborhood(request.neighborhood())
                        .build())
                .build();
    }

    public Person toEntity(VolunteerRequest request) {
        return Person.builder()
                .name(request.name())
                .isVolunteer(true)
                .email(request.email())
                .phone(request.phone())
                .role(request.role())
                .birthDate(calculateBirthYear(request.age()))
                .build();
    }

    private LocalDate calculateBirthYear(int age) {
        int currentYear = Year.now().getValue();
        int birthYear = currentYear - age;
        return LocalDate.of(birthYear, 1, 1);
    }

}
