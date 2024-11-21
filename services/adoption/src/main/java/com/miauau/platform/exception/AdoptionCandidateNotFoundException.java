package com.miauau.platform.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdoptionCandidateNotFoundException extends RuntimeException {
    private final String msg;
}
