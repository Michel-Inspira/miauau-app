package com.miauau.platform.requests;

public record InterestRequest(
        DogRequest dog,
        CatRequest cat
) {
}
