package com.miauau.platform.requests;

public record FoodTypeRequest(
        boolean animal,
        boolean human,
        boolean other,
        String otherDescription
) {
}
