package it.busz.empik.github.domain.external;

import it.busz.empik.github.infrastructure.api.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class GitHubServiceImpl implements GitHubService {
    private final Double followersDividend;
    private final Double publicReposAddition;

    private final GitHubClient gitHubClient;

    GitHubServiceImpl(GitHubClient gitHubClient,
                      @Value("${user.followers.dividend}") Double followersDividend,
                      @Value("${user.publicRepos.addition}") Double publicReposAddition) {
        this.gitHubClient = gitHubClient;
        this.followersDividend = followersDividend;
        this.publicReposAddition = publicReposAddition;
    }

    @Override
    public UserDto getUser(final String login) {
        final var user = gitHubClient.getUser(login);
        final var calculations = calculate(user);
        return UserMapper.externalToUserDto(user, calculations);
    }

    private Double calculate(UserExternalDto user) {
        final var followers = user.followers();
        if (followers == 0) {
            return 0.0;
        }

        final var publicRepos = user.publicRepos();
        return (followersDividend / followers) * (publicReposAddition + publicRepos);
    }
}
