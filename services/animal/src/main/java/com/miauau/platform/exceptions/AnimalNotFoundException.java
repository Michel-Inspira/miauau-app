package com.miauau.platform.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AnimalNotFoundException extends RuntimeException {
    private final String msg;
}
