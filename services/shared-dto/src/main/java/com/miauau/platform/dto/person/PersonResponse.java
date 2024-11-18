package com.miauau.platform.dto.person;

import java.time.LocalDate;
import java.util.UUID;

public record PersonResponse(
        UUID id,
        String name,
        Boolean isVolunteer,
        String role,
        String email,
        String phone,
        String cpf,
        String rg,
        LocalDate birthDate,
        String landline,
        // Address information
        String zipCode,
        String street,
        String number,
        String complement,
        String neighborhood
) {
}
