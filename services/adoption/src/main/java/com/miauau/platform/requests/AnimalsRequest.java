package com.miauau.platform.requests;

public record AnimalsRequest(
        PreviousAnimalsRequest previous,
        String adoptionMotivation,
        String adoptionMotivationDescription,
        boolean wantSpecificAnimal,
        String specificAnimal,
        AnimalsOfInterestRequest animalsOfInterest
) {
}
