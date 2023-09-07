package it.busz.empik.github.infrastructure;

import it.busz.empik.github.domain.GitHubFacade;
import it.busz.empik.github.infrastructure.api.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class GitHubController {
    private final GitHubFacade gitHubFacade;

    @GetMapping("/users/{login}")
    public UserDto getUser(@PathVariable @NonNull final String login) {
        return gitHubFacade.getUser(login);
    }
}
