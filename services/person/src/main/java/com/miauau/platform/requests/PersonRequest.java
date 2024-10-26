package com.miauau.platform.requests;

import jakarta.validation.constraints.NotNull;

public record PersonRequest(
        @NotNull(message = "Name is required")
        String name,
        Boolean isVolunteer,
        String role,
        @NotNull(message = "Name is required")
        String email,
        @NotNull(message = "Name is required")
        String phone,
        // Address information
        @NotNull(message = "Zip code is required")
        String zipCode,
        @NotNull(message = "Street is required")
        String street,
        @NotNull(message = "Number is required")
        String number,
        String complement,
        @NotNull(message = "Neighborhood is required")
        String neighborhood
) {
}
