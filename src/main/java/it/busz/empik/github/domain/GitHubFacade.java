package it.busz.empik.github.domain;

import it.busz.empik.github.domain.external.GitHubService;
import it.busz.empik.github.infrastructure.api.UserDto;
import it.busz.empik.web.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static it.busz.empik.web.GlobalErrorMessageCodes.LOGIN_CANNOT_BE_EMPTY;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitHubFacade {
    private static final Long DEFAULT_COUNTER = 1L;
    private final UserRepository userRepository;
    private final GitHubService gitHubService;

    public UserDto getUser(final String login) {
        if (login.isBlank()) { //TODO is it even possible?
            log.error("Login cannot be empty");
            throw new BadRequestException("Login cannot be empty", LOGIN_CANNOT_BE_EMPTY);
        }

        final var user = gitHubService.getUser(login);
        // TODO consider transactional? (czy to ma sens wgl)
        incrementRequestCount(login);
        return user;
    }

    private void incrementRequestCount(String login) {
        if (userRepository.existsByLoginIgnoreCase(login)) {
            log.info("Login {} already exists, incrementing counter", login);
            userRepository.updateCounter(login);
        } else {
            log.info("Login {} does not exist, saving user with default counter", login);
            final var user = new User(null, login, DEFAULT_COUNTER);
            userRepository.save(user);
            log.info("Login {} saved with id {}", login, user.getId());
        }
    }

}
