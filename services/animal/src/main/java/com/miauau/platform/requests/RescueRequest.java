package com.miauau.platform.requests;

public record RescueRequest(
        String howDidItArrive,
        String description,
        ResponsibleRequest responsible
) {
}

