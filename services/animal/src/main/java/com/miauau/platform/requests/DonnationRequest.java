package com.miauau.platform.requests;

public record DonnationRequest(
        boolean money,
        boolean food,
        boolean antiFleas,
        boolean timeToHelp,
        boolean other,
        String otherDescription
) {
}
