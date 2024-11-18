package com.miauau.platform.requests;

public record AgeGroupRequest(
        boolean puppy,
        boolean adult,
        boolean elderly
) {
}
