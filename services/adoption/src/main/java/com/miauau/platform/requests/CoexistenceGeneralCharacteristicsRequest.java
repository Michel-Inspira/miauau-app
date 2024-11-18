package com.miauau.platform.requests;

public record CoexistenceGeneralCharacteristicsRequest(
        String animalWillStay,
        String possibilityOfMoving,
        boolean livesAlone,
        String livesWithWho,
        int amountOfChildrenInTheHouse,
        String childrensAge,
        boolean allergicResidents,
        String whatHappensInCaseOfAllergies,
        boolean allResidentsAgree,
        boolean hasOtherAnimals,
        int numberOfAnimalsCurrently,
        boolean castrated
){
}