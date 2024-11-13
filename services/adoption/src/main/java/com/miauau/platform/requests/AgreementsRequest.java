package com.miauau.platform.requests;

import jakarta.validation.constraints.NotNull;

public record AgreementsRequest(
        boolean certaintyOfAdoption,
        boolean awareOfTheImportanceOfNeuteringTheAnimal,
        boolean agreesWithCastration,
        boolean longTermCommitment,
        boolean imageUse,
        boolean monetaryContribution,
        boolean houseVisit,
        boolean notifyBeforeDonateToSomeoneElse,
        boolean trueInformation,
        boolean videoPresentation
) {
}
