package com.miauau.platform.services;

import com.miauau.platform.dto.person.PersonResponse;
import com.miauau.platform.models.CandidateForm;
import com.miauau.platform.requests.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdoptionFormMapper {
    public CandidateForm toEntity(AdoptionFormRequest request, PersonResponse person){
        if (request == null) {
            return null;
        }
        return CandidateForm.builder()
                .personId(person.id().toString())
                .occupation(occupationRequestToMap(request.socioeconomicProfile().occupation()))
                .residence(residenceRequestToMap(request.socioeconomicProfile().residence()))
                .housingDetails(housingDetailsToMap(request.housingDetails()))
                .coexistence(coexistenceToMap(request.coexistence().generalCharacteristics()))
                .previousAnimals(previousAnimalsRequestToMap(request.animals().previous()))
                .adoptionMotivation(request.animals().adoptionMotivation())
                .adoptionMotivationDescription(request.animals().adoptionMotivationDescription())
                .animalsOfInterest(animalsOfInterestRequestToMap(request))
                .dailyCare(dailyCareRequestToMap(request.dailyCare()))
                .attitudesTowardsTheAnimal(attitudesTowardsTheAnimalRequestToMap(request.attitudesTowardsTheAnimal()))
                .agreements(agreementsRequestToMap(request.agreements()))
                .build();
    }
    private Map<String, Object> occupationRequestToMap(OccupationRequest occupation) {
        Map<String, Object> map = new HashMap<>();
        map.put("profession", occupation.profession());
        map.put("rent", occupation.rent());
        OccupationDetailsRequest details = occupation.occupation();
        map.put("working", details.working());
        map.put("studying", details.studying());
        map.put("unemployed", details.unemployed());
        map.put("other", details.other());
        map.put("otherDescription", details.otherDescription());
        return map;
    }
    private Map<String, Object> residenceRequestToMap(ResidenceRequest residenceRequest) {
        Map<String, Object> map = new HashMap<>();

        String selectedSituation = "";
        if (residenceRequest.own()) {
            selectedSituation = "own";
        } else if (residenceRequest.rent()) {
            selectedSituation = "rent";
        } else if (residenceRequest.inherited()) {
            selectedSituation = "inherited";
        }
        map.put("situation", selectedSituation);

        String selectedType = "";
        ResidenceTypeRequest type = residenceRequest.type();
        if (type.house()) {
            selectedType = "house";
        } else if (type.apartment()) {
            selectedType = "apartment";
        } else if (type.grange()) {
            selectedType = "grange";
        } else if (type.other()) {
            selectedType = "other";
        }
        map.put("type", selectedType);
        map.put("otherTypeDescription", type.otherDescription());
        return map;
    }
    private Map<String, Object> housingDetailsToMap(HousingDetailsRequest request) {
        Map<String, Object> map = new HashMap<>();
        HousingGeneralCharacteristicsRequest generalRequest = request.generalCharacteristics();
        map.put("pool", generalRequest.pool());
        map.put("poolWithProtection", generalRequest.poolWithProtection());
        map.put("fence", generalRequest.fence());
        map.put("wall", generalRequest.wall());
        map.put("windowsWithScreen", generalRequest.windowsWithScreen());
        map.put("balconyWithScreen", generalRequest.balconyWithScreen());
        map.put("willInstallScreens", generalRequest.willInstallScreens());
        map.put("yard", generalRequest.yard());
        map.put("safeHouse", generalRequest.safeHouse());
        map.put("flightRisk", generalRequest.flightRisk());
        map.put("condominiumRestriction", generalRequest.condominiumRestriction());
        return map;
    }
    private Map<String, Object> coexistenceToMap(CoexistenceGeneralCharacteristicsRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("animalWillStay", request.animalWillStay());
        map.put("possibilityOfMoving", request.possibilityOfMoving());
        map.put("livesAlone", request.livesAlone());
        if (!request.livesAlone()){
            map.put("livesWithWho", request.livesWithWho());
        }
        map.put("amountOfChildrenInTheHouse", request.amountOfChildrenInTheHouse());
        if(request.amountOfChildrenInTheHouse() > 0){
            map.put("childrensAge", request.childrensAge());
        }
        map.put("allergicResidents", request.allergicResidents());
        if(request.allergicResidents()){
            map.put("whatHappensInCaseOfAllergies", request.whatHappensInCaseOfAllergies());
        }
        map.put("allResidentsAgree", request.allResidentsAgree());
        map.put("hasOtherAnimals", request.hasOtherAnimals());
        if(request.hasOtherAnimals()){
            map.put("numberOfAnimalsCurrently", request.numberOfAnimalsCurrently());
            map.put("castrated", request.castrated());
        }
        return map;
    }
    private Map<String, Object> previousAnimalsRequestToMap(PreviousAnimalsRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("hadAnimalsBefore", request.hadAnimalsBefore());
        if(request.hadAnimalsBefore()){
            map.put("whatHappenedToLastAnimal", request.whatHappenedToLastAnimal());
            map.put("dateOfOccurrence", LocalDate.parse(request.dateOfOccurrence())); // ver se não quebra
        }
        return map;
    }
    private Map<String, Map<String, Boolean>> animalsOfInterestRequestToMap(AdoptionFormRequest request) {
        Map<String, Map<String, Boolean>> map = new HashMap<>();
        if(request.animals().animalsOfInterest().dog()){
            Map<String, Boolean> dogs = new HashMap<>();
            dogs.put("male", request.interest().dog().sex().male());
            dogs.put("female", request.interest().dog().sex().female());
            dogs.put("small", request.interest().dog().size().small());
            dogs.put("medium", request.interest().dog().size().medium());
            dogs.put("big", request.interest().dog().size().big());
            dogs.put("puppy", request.interest().dog().ageGroup().puppy());
            dogs.put("adult", request.interest().dog().ageGroup().adult());
            dogs.put("elderly", request.interest().dog().ageGroup().elderly());
            map.put("dog", dogs);
        }
        if(request.animals().animalsOfInterest().cat()){
            Map<String, Boolean> cats = new HashMap<>();
            cats.put("male", request.interest().cat().sex().male());
            cats.put("female", request.interest().cat().sex().female());
            cats.put("small", request.interest().cat().size().small());
            cats.put("medium", request.interest().cat().size().medium());
            cats.put("big", request.interest().cat().size().big());
            cats.put("puppy", request.interest().cat().ageGroup().puppy());
            cats.put("adult", request.interest().cat().ageGroup().adult());
            cats.put("elderly", request.interest().cat().ageGroup().elderly());
            map.put("cat", cats);
        }
        return map;
    }
    public Map<String, Object> dailyCareRequestToMap(DailyCareRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("responsibleForCare", request.responsibleForCare());
        map.put("responsibleForCareInCaseOfTravel", request.responsibleForCareInCaseOfTravel());
        map.put("howWillEducate", request.howWillEducate());
        map.put("hasPetCarrier", request.hasPetCarrier());
        map.put("dailyWalks", request.dailyWalks());
        map.put("timeAlone", request.timeAlone());
        FoodTypeRequest foodType = request.foodType();
        String selectedFoodType = "";
        if(foodType.animal()){
            selectedFoodType = "animal";
        } else if(foodType.human()){
            selectedFoodType = "human";
        }else if(foodType.other()){
            selectedFoodType = "other";
            map.put("foodTypeOtherDescription", foodType.otherDescription());
        }
        map.put("foodType", selectedFoodType);
        return map;
    }
    public Map<String, Object> attitudesTowardsTheAnimalRequestToMap(AttitudesTowardsTheAnimalRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("getsLost", request.getsLost());
        map.put("getsSickOrAccident", request.getsSickOrAccident());
        map.put("hurtsYourChild", request.hurtsYourChild());
        map.put("damagesValuableObject", request.damagesValuableObject());
        map.put("peesOrPoopsInInappropriatePlace", request.peesOrPoopsInInappropriatePlace());
        map.put("doesThingsYouDontWant", request.doesThingsYouDontWant());
        map.put("ifYouHaveAChild", request.ifYouHaveAChild());
        return map;
    }
    public Map<String, Boolean> agreementsRequestToMap(AgreementsRequest request) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("certaintyOfAdoption", request.certaintyOfAdoption());
        map.put("awareOfTheImportanceOfNeuteringTheAnimal", request.awareOfTheImportanceOfNeuteringTheAnimal());
        map.put("agreesWithCastration", request.agreesWithCastration());
        map.put("longTermCommitment", request.longTermCommitment());
        map.put("imageUse", request.imageUse());
        map.put("monetaryContribution", request.monetaryContribution());
        map.put("houseVisit", request.houseVisit());
        map.put("notifyBeforeDonateToSomeoneElse", request.notifyBeforeDonateToSomeoneElse());
        map.put("trueInformation", request.trueInformation());
        map.put("videoPresentation", request.videoPresentation());
        return map;
    }

}
