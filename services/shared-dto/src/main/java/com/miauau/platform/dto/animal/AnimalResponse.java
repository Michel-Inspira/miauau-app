package com.miauau.platform.dto.animal;

public record AnimalResponse(
        String id,
        String name,
        String imagePath,
        String type,
        String ageGroup,
        String sex,
        boolean isAdopted,
        String ongId
) {
}
