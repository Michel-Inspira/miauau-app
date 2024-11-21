package com.miauau.platform.services;

import com.miauau.platform.clients.AnimalClient;
import com.miauau.platform.clients.PersonClient;
import com.miauau.platform.dto.adoption.AdoptionCandidateResponse;
import com.miauau.platform.dto.adoption.DetailedAdoptionCandidateResponse;
import com.miauau.platform.dto.animal.AnimalResponse;
import com.miauau.platform.dto.person.PersonResponse;
import com.miauau.platform.models.CandidateForm;
import com.miauau.platform.requests.*;
import com.miauau.platform.utils.MapUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdoptionFormMapper {
    private final PersonClient personClient;
    private final AnimalClient animalClient;

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
        String selectedType = residenceRequest.type();
        map.put("type", selectedType);
        if(selectedType.equals("other")){
            map.put("otherTypeDescription", residenceRequest.otherDescription());
        }
        map.put("situation", residenceRequest.situation());
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
    private Map<String, Object> animalsOfInterestRequestToMap(AdoptionFormRequest request) {
        Map<String, Object> map = new HashMap<>();
        if(request.animals().wantSpecificAnimal()){
            map.put("specificAnimal", request.animals().specificAnimal());
        } else {
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
        }
        return map;
    }
    private Map<String, Object> dailyCareRequestToMap(DailyCareRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("responsibleForCare", request.responsibleForCare());
        map.put("responsibleForCareInCaseOfTravel", request.responsibleForCareInCaseOfTravel());
        map.put("howWillEducate", request.howWillEducate());
        map.put("hasPetCarrier", request.hasPetCarrier());
        map.put("dailyWalks", request.dailyWalks());
        map.put("timeAlone", request.timeAlone());
        map.put("foodType", request.foodType());
        return map;
    }
    private Map<String, Object> attitudesTowardsTheAnimalRequestToMap(AttitudesTowardsTheAnimalRequest request) {
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
    private Map<String, Boolean> agreementsRequestToMap(AgreementsRequest request) {
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
    public AdoptionCandidateResponse toResponse(CandidateForm candidate){
        if (candidate == null) {
            return null;
        }

        PersonResponse person = personClient.getPersonById(UUID.fromString(candidate.getPersonId()));
        return new AdoptionCandidateResponse(
                candidate.getId(),
                person.name(),
                this.calculateAge(person.birthDate()),
                this.getOccupation(candidate.getOccupation()),
                this.getLivingSituation(candidate.getCoexistence())
        );
    }
    private String calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return "? anos";
        }
        return Period.between(birthDate, LocalDate.now()).getYears() + " anos";
    }
    private String getOccupation(Map<String, Object> occupation) {
        String occupationStr = "";
        if (occupation != null) {
            try {
                Boolean working = (Boolean) occupation.get("working");
                Boolean studying = (Boolean) occupation.get("studying");
                Boolean unemployed = (Boolean) occupation.get("unemployed");
                Boolean other = (Boolean) occupation.get("other");
                if (working && studying) {
                    occupationStr = "Trabalha e estuda";
                } else if (working) {
                    occupationStr = "Trabalha";
                } else if (studying) {
                    occupationStr = "Estuda";
                } else if (unemployed) {
                    occupationStr = "Desempregado";
                } else if (other) {
                    occupationStr = (String) occupation.get("otherDescription");
                }
            } catch (Exception ignored) {}
        }
        return occupationStr;
    }
    private String getLivingSituation(Map<String, Object> coexistence) {
        if (coexistence != null) {
            Object livesAlone = coexistence.get("livesAlone");
            if (livesAlone instanceof Boolean) {
                return (Boolean)livesAlone ? "Mora sozinho" : "Não mora sozinho";
            }
        }
        return "";
    }

    public DetailedAdoptionCandidateResponse toDetailedResponse(CandidateForm candidate){
        if (candidate == null) {
            return null;
        }

        PersonResponse person = personClient.getPersonById(UUID.fromString(candidate.getPersonId()));
        return new DetailedAdoptionCandidateResponse(
                this.getPersonalInformation(person),
                this.getSocioeconomicProfile(candidate),
                this.getHousingDetails(candidate),
                this.getCoexistence(candidate),
                this.getAnimals(candidate),
                this.getInterest(candidate),
                this.getDailyCare(candidate),
                this.getAttitudesTowardsTheAnimal(candidate),
                this.getAgreements(candidate)
        );
    }

    private Map<String, Object> getPersonalInformation(PersonResponse person) {
        Map<String, Object> info =  new HashMap<>();

        Map<String, String> identification =  new HashMap<>();
        identification.put("name", person.name());
        identification.put("cpf", person.cpf());
        identification.put("rg", person.rg());
        identification.put("birthDate", String.valueOf(person.birthDate()));
        identification.put("phone", person.phone());
        identification.put("landline", person.landline());
        identification.put("email", person.email());
        info.put("identification", identification);

        Map<String, String> address =  new HashMap<>();
        address.put("zipcode", person.zipCode());
        address.put("street", person.street());
        address.put("number", person.number());
        address.put("complement", person.complement());
        address.put("neighborhood", person.neighborhood());
        info.put("address", address);

        return info;
    }
    private Map<String, Object> getSocioeconomicProfile(CandidateForm candidate) {
        Map<String, Object> occupationData =  candidate.getOccupation();

        Map<String, Object> occupation =  new HashMap<>();
        occupation.put("profession", MapUtils.tryGetStringValue(occupationData, "profession"));
        occupation.put("rent", MapUtils.tryGetStringValue(occupationData, "rent"));

        Map<String, Object> innerOccupation =  new HashMap<>();
        innerOccupation.put("working", MapUtils.tryGetBooleanValue(occupationData, "working"));
        innerOccupation.put("studying", MapUtils.tryGetBooleanValue(occupationData, "studying"));
        innerOccupation.put("unemployed", MapUtils.tryGetBooleanValue(occupationData, "unemployed"));
        innerOccupation.put("other", MapUtils.tryGetBooleanValue(occupationData, "other"));
        innerOccupation.put("otherDescription", MapUtils.tryGetStringValue(occupationData, "otherDescription"));
        occupation.put("occupation", innerOccupation);

        Map<String, Object> residenceData =  candidate.getResidence();
        Map<String, String> residence =  new HashMap<>();
        residence.put("type", MapUtils.tryGetStringValue(residenceData, "type"));
        residence.put("otherDescription", MapUtils.tryGetStringValue(residenceData, "otherDescription"));
        residence.put("situation", MapUtils.tryGetStringValue(residenceData, "situation"));

        Map<String, Object> socioeconomicProfile =  new HashMap<>();
        socioeconomicProfile.put("occupation", occupation);
        socioeconomicProfile.put("residence", residence);
        return socioeconomicProfile;

    }
    private Map<String, Object> getHousingDetails(CandidateForm candidate) {
        Map<String, Object> housingDetailsData =  candidate.getHousingDetails();
        Map<String, Object> housingDetails =  new HashMap<>();
        Map<String, Object> generalCharacteristics =  new HashMap<>();
        generalCharacteristics.put("pool", MapUtils.tryGetBooleanValue(housingDetailsData, "pool"));
        generalCharacteristics.put("poolWithProtection", MapUtils.tryGetBooleanValue(housingDetailsData, "poolWithProtection"));
        generalCharacteristics.put("fence", MapUtils.tryGetBooleanValue(housingDetailsData, "fence"));
        generalCharacteristics.put("wall", MapUtils.tryGetBooleanValue(housingDetailsData, "wall"));
        generalCharacteristics.put("windowsWithScreen", MapUtils.tryGetBooleanValue(housingDetailsData, "windowsWithScreen"));
        generalCharacteristics.put("balconyWithScreen", MapUtils.tryGetBooleanValue(housingDetailsData, "balconyWithScreen"));
        generalCharacteristics.put("willInstallScreens", MapUtils.tryGetBooleanValue(housingDetailsData, "willInstallScreens"));
        generalCharacteristics.put("safeHouse", MapUtils.tryGetBooleanValue(housingDetailsData, "safeHouse"));
        generalCharacteristics.put("flightRisk", MapUtils.tryGetBooleanValue(housingDetailsData, "flightRisk"));
        generalCharacteristics.put("yard", MapUtils.tryGetStringValue(housingDetailsData, "yard"));
        generalCharacteristics.put("condominiumRestriction", MapUtils.tryGetStringValue(housingDetailsData, "condominiumRestriction"));

        housingDetails.put("generalCharacteristics", generalCharacteristics);
        return housingDetails;

    }
    private Map<String, Object> getCoexistence(CandidateForm candidate) {
        Map<String, Object> coexistenceData =  candidate.getCoexistence();
        Map<String, Object> coexistence =  new HashMap<>();
        Map<String, Object> generalCharacteristics =  new HashMap<>();
        generalCharacteristics.put("animalWillStay", MapUtils.tryGetStringValue(coexistenceData, "animalWillStay"));
        generalCharacteristics.put("possibilityOfMoving", MapUtils.tryGetStringValue(coexistenceData, "possibilityOfMoving"));
        generalCharacteristics.put("livesAlone", MapUtils.tryGetBooleanValue(coexistenceData, "livesAlone"));
        generalCharacteristics.put("amountOfChildrenInTheHouse", MapUtils.tryGetIntValue(coexistenceData, "amountOfChildrenInTheHouse"));
        generalCharacteristics.put("childrensAge", MapUtils.tryGetStringValue(coexistenceData, "childrensAge"));
        generalCharacteristics.put("allergicResidents", MapUtils.tryGetBooleanValue(coexistenceData, "allergicResidents"));
        generalCharacteristics.put("whatHappensInCaseOfAllergies", MapUtils.tryGetStringValue(coexistenceData, "whatHappensInCaseOfAllergies"));
        generalCharacteristics.put("allResidentsAgree", MapUtils.tryGetBooleanValue(coexistenceData, "allResidentsAgree"));
        generalCharacteristics.put("hasOtherAnimals", MapUtils.tryGetBooleanValue(coexistenceData, "hasOtherAnimals"));
        generalCharacteristics.put("castrated", MapUtils.tryGetBooleanValue(coexistenceData, "castrated"));
        generalCharacteristics.put("numberOfAnimalsCurrently", MapUtils.tryGetIntValue(coexistenceData, "numberOfAnimalsCurrently"));
        coexistence.put("generalCharacteristics", generalCharacteristics);
        return coexistence;
    }
    private Map<String, Object> getAnimals(CandidateForm candidate) {
        Map<String, Object> previousAnimalsData =  candidate.getPreviousAnimals();
        Map<String, Object> animalsOfInterestData =  candidate.getAnimalsOfInterest();
        Map<String, Object> previous =  new HashMap<>();
        previous.put("hadAnimalsBefore", MapUtils.tryGetBooleanValue(previousAnimalsData, "hadAnimalsBefore"));
        previous.put("whatHappenedToLastAnimal", MapUtils.tryGetStringValue(previousAnimalsData, "whatHappenedToLastAnimal"));
        previous.put("dateOfOccurrence", MapUtils.tryGetLocalDateValue(previousAnimalsData, "dateOfOccurrence"));

        Map<String, Object> animals =  new HashMap<>();
        animals.put("previous", previous);
        animals.put("adoptionMotivation", candidate.getAdoptionMotivation());
        animals.put("adoptionMotivationDescription", candidate.getAdoptionMotivationDescription());

        String specificAnimalId = MapUtils.tryGetStringValue(animalsOfInterestData, "specificAnimal");
        boolean wantSpecificAnimal = !specificAnimalId.isEmpty();
        animals.put("wantSpecificAnimal", wantSpecificAnimal);
        animals.put("specificAnimal", tryGetSpecificAnimalName(specificAnimalId));

        Map<String, Object> animalsOfInterest =  new HashMap<>();
        animalsOfInterest.put("cat", animalsOfInterestData.containsKey("cat"));
        animalsOfInterest.put("dog", animalsOfInterestData.containsKey("dog"));
        animals.put("animalsOfInterest", animalsOfInterest);

        return animals;
    }
    private String tryGetSpecificAnimalName(String specificAnimalId){
        try{
            AnimalResponse animal = animalClient.getAnimalById(specificAnimalId);
            return animal.name();
        } catch (Exception e) {
            return "";
        }
    }

    private Map<String, Object> getInterest(CandidateForm candidate) {
        Map<String, Object> animalsOfInterestData =  candidate.getAnimalsOfInterest();
        Map<String, Object> dog = new HashMap<>();
        if(animalsOfInterestData.containsKey("dog")){
            dog = setAnimalInterest(animalsOfInterestData, "dog");
        } else {
            dog = setEmptyAnimalInterest();
        }
        Map<String, Object> cat =  new HashMap<>();
        if(animalsOfInterestData.containsKey("cat")){
            cat = setAnimalInterest(animalsOfInterestData, "cat");
        } else {
            cat = setEmptyAnimalInterest();
        }

        Map<String, Object> interest =  new HashMap<>();
        interest.put("cat", cat);
        interest.put("dog", dog);
        return  interest;
    }

    private Map<String, Object> setAnimalInterest(Map<String, Object> data, String animalKey) {
        Map<String, Object> animalData = MapUtils.tryGetMapValue(data, animalKey);
        Map<String, Object> animal =  new HashMap<>();

        Map<String, Boolean> sex =  new HashMap<>();
        sex.put("female", MapUtils.tryGetBooleanValue(animalData, "female"));
        sex.put("male", MapUtils.tryGetBooleanValue(animalData, "male"));
        animal.put("sex", sex);

        Map<String, Boolean> size =  new HashMap<>();
        size.put("small", MapUtils.tryGetBooleanValue(animalData, "small"));
        size.put("medium", MapUtils.tryGetBooleanValue(animalData, "medium"));
        size.put("big", MapUtils.tryGetBooleanValue(animalData, "big"));
        animal.put("size", size);

        Map<String, Boolean> ageGroup =  new HashMap<>();
        ageGroup.put("puppy", MapUtils.tryGetBooleanValue(animalData, "puppy"));
        ageGroup.put("adult", MapUtils.tryGetBooleanValue(animalData, "adult"));
        ageGroup.put("elderly", MapUtils.tryGetBooleanValue(animalData, "elderly"));
        animal.put("ageGroup", ageGroup);
        return animal;
    }
    private Map<String, Object> setEmptyAnimalInterest(){
        Map<String, Object> animal =  new HashMap<>();

        Map<String, Boolean> sex =  new HashMap<>();
        sex.put("female", false);
        sex.put("male", false);
        animal.put("sex", sex);

        Map<String, Boolean> size =  new HashMap<>();
        size.put("small", false);
        size.put("medium", false);
        size.put("big", false);
        animal.put("size", size);

        Map<String, Boolean> ageGroup =  new HashMap<>();
        ageGroup.put("puppy", false);
        ageGroup.put("adult", false);
        ageGroup.put("elderly", false);
        animal.put("ageGroup", ageGroup);

        return  animal;
    }
    private Map<String, Object> getDailyCare(CandidateForm candidate) {
        Map<String, Object> dailyCareData = candidate.getDailyCare();
        Map<String, Object> dailyCare = new HashMap<>();
        dailyCare.put("responsibleForCare", MapUtils.tryGetStringValue(dailyCareData, "responsibleForCare"));
        dailyCare.put("responsibleForCareInCaseOfTravel", MapUtils.tryGetStringValue(dailyCareData, "responsibleForCareInCaseOfTravel"));
        dailyCare.put("howWillEducate", MapUtils.tryGetStringValue(dailyCareData, "howWillEducate"));
        dailyCare.put("hasPetCarrier", MapUtils.tryGetBooleanValue(dailyCareData, "hasPetCarrier"));
        dailyCare.put("dailyWalks", MapUtils.tryGetIntValue(dailyCareData, "dailyWalks"));
        dailyCare.put("timeAlone", MapUtils.tryGetStringValue(dailyCareData, "timeAlone"));
        dailyCare.put("foodType", MapUtils.tryGetStringValue(dailyCareData, "foodType"));
        return dailyCare;
    }
    private Map<String, Object> getAttitudesTowardsTheAnimal(CandidateForm candidate) {
        Map<String, Object> attitudesTowardsTheAnimalData = candidate.getAttitudesTowardsTheAnimal();
        Map<String, Object> attitudesTowardsTheAnimal = new HashMap<>();
        attitudesTowardsTheAnimal.put("getsLost", MapUtils.tryGetStringValue(attitudesTowardsTheAnimalData, "getsLost"));
        attitudesTowardsTheAnimal.put("getsSickOrAccident", MapUtils.tryGetStringValue(attitudesTowardsTheAnimalData, "getsSickOrAccident"));
        attitudesTowardsTheAnimal.put("hurtsYourChild", MapUtils.tryGetStringValue(attitudesTowardsTheAnimalData, "hurtsYourChild"));
        attitudesTowardsTheAnimal.put("damagesValuableObject", MapUtils.tryGetStringValue(attitudesTowardsTheAnimalData, "damagesValuableObject"));
        attitudesTowardsTheAnimal.put("peesOrPoopsInInappropriatePlace", MapUtils.tryGetStringValue(attitudesTowardsTheAnimalData, "peesOrPoopsInInappropriatePlace"));
        attitudesTowardsTheAnimal.put("doesThingsYouDontWant", MapUtils.tryGetStringValue(attitudesTowardsTheAnimalData, "doesThingsYouDontWant"));
        attitudesTowardsTheAnimal.put("ifYouHaveAChild", MapUtils.tryGetStringValue(attitudesTowardsTheAnimalData, "ifYouHaveAChild"));
        return attitudesTowardsTheAnimal;
    }
    private Map<String, Boolean> getAgreements(CandidateForm candidate) {
        Map<String, Boolean> agreementsData = candidate.getAgreements();
        Map<String, Boolean> agreements = new HashMap<>();
        agreements.put("certaintyOfAdoption", MapUtils.tryGetBooleanValue(agreementsData, "certaintyOfAdoption"));
        agreements.put("awareOfTheImportanceOfNeuteringTheAnimal", MapUtils.tryGetBooleanValue(agreementsData, "awareOfTheImportanceOfNeuteringTheAnimal"));
        agreements.put("agreesWithCastration", MapUtils.tryGetBooleanValue(agreementsData, "agreesWithCastration"));
        agreements.put("longTermCommitment", MapUtils.tryGetBooleanValue(agreementsData, "longTermCommitment"));
        agreements.put("imageUse", MapUtils.tryGetBooleanValue(agreementsData, "imageUse"));
        agreements.put("monetaryContribution", MapUtils.tryGetBooleanValue(agreementsData, "monetaryContribution"));
        agreements.put("houseVisit", MapUtils.tryGetBooleanValue(agreementsData, "houseVisit"));
        agreements.put("notifyBeforeDonateToSomeoneElse", MapUtils.tryGetBooleanValue(agreementsData, "notifyBeforeDonateToSomeoneElse"));
        agreements.put("trueInformation", MapUtils.tryGetBooleanValue(agreementsData, "trueInformation"));
        agreements.put("videoPresentation", MapUtils.tryGetBooleanValue(agreementsData, "videoPresentation"));

        return agreements;
    }


}
