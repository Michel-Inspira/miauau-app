package com.miauau.platform.requests;

public record SizeRequest(
        boolean small,
        boolean medium,
        boolean big
) {
}
