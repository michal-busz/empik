package it.busz.empik.github.domain.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
class GitHubClient {
    private final String userApiUrl;
    private final RestTemplate restTemplate;

    public GitHubClient(
            @Value("${github.api.user.url}") String userApiUrl,
            RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
        this.userApiUrl = userApiUrl;
    }

    public UserExternalDto getUser(final String login) {
        try {
            final var requestUrl = userApiUrl + login;
            final var response = restTemplate.getForEntity(requestUrl, UserExternalDto.class);
            if (!response.hasBody()) {
                log.warn("Response contains empty body for login {}", login);
                throw new IllegalStateException("Response from GitHub is empty/null");
            }
            return response.getBody();
        } catch (RestClientException e) {
            log.error("Exception occurred upon getting user with login {}", login, e);
            throw e;
        }
    }
}
