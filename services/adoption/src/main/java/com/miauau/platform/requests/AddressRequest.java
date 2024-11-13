package com.miauau.platform.requests;

public record AddressRequest(
        String zipcode,
        String street,
        String number,
        String complement,
        String neighborhood
) {
}
