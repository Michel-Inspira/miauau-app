package com.miauau.platform.requests;

public record PreviousAnimalsRequest(
        boolean hadAnimalsBefore,
        String whatHappenedToLastAnimal,
        String dateOfOccurrence
) {
}
