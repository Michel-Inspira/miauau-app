package com.miauau.platform.requests;

public record AdoptionFormRequest(
        PersonalInformationRequest personalInformation,
        SocioeconomicProfileRequest socioeconomicProfile,
        HousingDetailsRequest housingDetails,
        CoexistenceRequest coexistence,
        AnimalsRequest animals,
        InterestRequest interest,
        DailyCareRequest dailyCare,
        AttitudesTowardsTheAnimalRequest attitudesTowardsTheAnimal,
        AgreementsRequest agreements
) {
}
