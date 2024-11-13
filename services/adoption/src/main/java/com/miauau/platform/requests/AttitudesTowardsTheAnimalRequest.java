package com.miauau.platform.requests;

public record AttitudesTowardsTheAnimalRequest(
		String getsLost,
        String getsSickOrAccident,
        String hurtsYourChild,
        String damagesValuableObject,
        String peesOrPoopsInInappropriatePlace,
        String doesThingsYouDontWant,
        String ifYouHaveAChild
) {
}
