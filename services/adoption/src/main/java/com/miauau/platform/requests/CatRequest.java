package com.miauau.platform.requests;

public record CatRequest(
        SexRequest sex,
        SizeRequest size,
        AgeGroupRequest ageGroup
) {
}
