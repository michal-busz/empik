package it.busz.empik.github.domain;

import it.busz.empik.github.domain.external.GitHubService;
import it.busz.empik.github.infrastructure.api.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GitHubFacade {
    private final UserRepository userRepository;
    private final GitHubService gitHubService;

    public GitHubFacade(UserRepository userRepository, GitHubService gitHubService) {
        this.userRepository = userRepository;
        this.gitHubService = gitHubService;
    }

    public UserDto getUser(final String login) {
        final var user = gitHubService.getUser(login);
        incrementCountOrSaveUser(login);
        return user;
    }

    private void incrementCountOrSaveUser(String login) {
        if (userRepository.existsByLoginIgnoreCase(login)) {
            userRepository.incrementCounter(login);
        } else {
            log.info("Login {} does not exist, saving user with default counter", login);
            final var user = new User(login);
            userRepository.save(user);
        }
    }

}
