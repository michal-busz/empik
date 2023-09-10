package it.busz.empik.github.domain.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
class GitHubClient {
    private final String usersApiUrl;
    private final RestTemplate restTemplate;

    public GitHubClient(
            @Value("${github.api.users.url}") String usersApiUrl,
            RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
        this.usersApiUrl = usersApiUrl;
    }

    public UserExternalDto getUser(final String login) {
        try {
            final var requestUrl = usersApiUrl + login;
            final var response = restTemplate.getForEntity(requestUrl, UserExternalDto.class);
            return response.getBody();
        } catch (RestClientException e) {
            log.error("Exception occurred upon getting user with login {} ", login, e);
            throw e;
        }
    }
}
