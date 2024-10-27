package com.miauau.platform.email;

import lombok.Getter;

public enum EmailTemplates {

    ADOPTION_REQUEST_CONFIRMATION("adoption-request-confirmation.html", "Adoption Request Confirmation"),
    ADOPTION_FORM_REVIEW("adoption-form-review.html", "Adoption Request Review"),
    ADOPTION_FORM_REJECTION("adoption-form-rejection.html", "Adoption Request Rejection"),
    ADOPTION_FORM_APPROVAL("adoption-form-approval.html", "Adoption Request Approval");

    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
