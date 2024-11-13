package com.miauau.platform.requests;

public record IdentificationRequest(
        String name,
        String cpf,
        String rg,
        String birthDate,
        String phone,
        String landline,
        String email
) {
}
