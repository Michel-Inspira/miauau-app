package com.miauau.platform.responses;

import java.util.UUID;

public record PersonResponse(
        UUID id,
        String name,
        Boolean isVolunteer,
        String role,
        String email,
        String phone,
        // Address information
        String zipCode,
        String street,
        String number,
        String complement,
        String neighborhood
) {
}
