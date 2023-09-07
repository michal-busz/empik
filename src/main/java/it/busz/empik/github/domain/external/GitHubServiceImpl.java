package it.busz.empik.github.domain.external;

import it.busz.empik.github.infrastructure.api.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class GitHubServiceImpl implements GitHubService {
    private static final Double DEFAULT_BASE = 6.0;
    private static final Double PUBLIC_REPOS_INCREMENT = 2.0;

    private final GitHubClient gitHubClient;

    @Override
    public UserDto getUser(final String login) {
        final var user = gitHubClient.getUser(login);
        final var calculations = calculate(user);
        return UserMapper.map(user, calculations);
    }

    private Double calculate(UserExternalDto user) {
        final var followers = user.followers();
        final var publicRepos = user.publicRepos();
        //TODO consider log if followers is 0 (division by 0 results in infinity)
        return (DEFAULT_BASE / followers) * (PUBLIC_REPOS_INCREMENT + publicRepos);
    }
}
