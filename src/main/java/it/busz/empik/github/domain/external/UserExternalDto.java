package it.busz.empik.github.domain.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
record UserExternalDto(
        Long id,
        String login,
        String name,
        String type,
        @JsonProperty(value = "avatar_url")
        URI avatarUrl,
        @JsonProperty(value = "created_at")
        Instant createdAt,
        Long followers,
        @JsonProperty(value = "public_repos")
        Long publicRepos
) {
}
