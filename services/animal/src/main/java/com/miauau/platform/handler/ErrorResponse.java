package com.miauau.platform.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
