package it.busz.empik.data.model;

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
