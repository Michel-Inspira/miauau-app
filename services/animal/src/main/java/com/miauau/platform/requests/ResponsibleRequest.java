package com.miauau.platform.requests;

public record ResponsibleRequest(
        String name,
        String phone,
        DonnationRequest donnation
) {
}
