package com.miauau.platform.requests;

public record VolunteerRequest(
    String name,
    String phone,
    String email,
    Integer age,
    String role
) {
}

