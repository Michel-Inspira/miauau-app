package com.miauau.platform.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PersonNotFoundException extends RuntimeException {
    private final String msg;
}
