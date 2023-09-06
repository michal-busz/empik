package it.busz.empik.service;

import it.busz.empik.data.UserMapper;
import it.busz.empik.data.model.User;
import it.busz.empik.data.model.UserDto;
import it.busz.empik.data.model.UserExternalDto;
import it.busz.empik.data.repo.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GitHubService {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private static final String URL = "https://api.github.com/users/"; //TODO move to properties
    private static final Long DEFAULT_COUNTER = 1L;
    private static final Double DEFAULT_BASE = 6.0;
    private static final Double PUBLIC_REPOS_INCREMENT = 2.0;

    public UserDto getUser(String login){ //TODO improve name
        // TODO consider transactional
        final var requestUrl = URL + login;
        final var response = restTemplate.getForEntity(requestUrl, UserExternalDto.class);
        final var user = response.getBody();
        if(user == null){
            return null; // TODO throw exception
        }
        incrementRequestCount(login);
        final var calculations = calculate(user);
        return UserMapper.map(user, calculations); //TODO consider checking null
    }

    private Double calculate(UserExternalDto user){
        final var followers = user.followers();
        final var publicRepos = user.publicRepos();
        return (DEFAULT_BASE / followers) * (PUBLIC_REPOS_INCREMENT + publicRepos);
    }

    private void incrementRequestCount(String login){
        if(userRepository.existsByLoginIgnoreCase(login)){
            userRepository.updateCounter(login);
        }
        else{
            final var user = new User(null, login, DEFAULT_COUNTER);
            userRepository.save(user);
        }
    }

}
