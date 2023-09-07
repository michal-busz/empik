package it.busz.empik.github.infrastructure.api;

import java.net.URI;
import java.time.Instant;

public record UserDto(
        Long id,
        String login,
        String name,
        String type,
        URI avatarUrl,
        Instant createdAt,
        Double calculations
) {
}
