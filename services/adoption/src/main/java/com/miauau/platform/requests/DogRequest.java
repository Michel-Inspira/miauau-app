package com.miauau.platform.requests;

public record DogRequest(
        SexRequest sex,
        SizeRequest size,
        AgeGroupRequest ageGroup
) {
}
