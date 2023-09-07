package it.busz.empik.github.domain.external;

import it.busz.empik.github.infrastructure.api.UserDto;

public interface GitHubService {
    UserDto getUser(String login);
}
