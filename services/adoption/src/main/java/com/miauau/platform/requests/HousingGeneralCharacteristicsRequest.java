package com.miauau.platform.requests;

public record HousingGeneralCharacteristicsRequest(
        boolean pool,
        boolean poolWithProtection,
        boolean fence,
        boolean wall,
        boolean windowsWithScreen,
        boolean balconyWithScreen,
        boolean willInstallScreens,
        String yard,
        boolean safeHouse,
        boolean flightRisk,
        String condominiumRestriction
){}
