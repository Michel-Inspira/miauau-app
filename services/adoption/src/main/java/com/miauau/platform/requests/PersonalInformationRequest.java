package com.miauau.platform.requests;

public record PersonalInformationRequest(
        IdentificationRequest identification,
        AddressRequest address
) {
}
