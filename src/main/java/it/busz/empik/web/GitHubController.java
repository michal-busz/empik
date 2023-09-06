package it.busz.empik.web;

import it.busz.empik.data.model.UserDto;
import it.busz.empik.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GitHubController {
    private final GitHubService gitHubService;

    @GetMapping("/users/{login}")
    public UserDto getUser(@PathVariable String login){
        return gitHubService.getUser(login);
    }
}
