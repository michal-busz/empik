package it.busz.empik.github.domain;

import it.busz.empik.github.domain.external.GitHubService;
import it.busz.empik.github.infrastructure.api.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.time.Instant;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GitHubFacadeTest {
    private static final String exampleLogin = "login";
    @Mock
    private GitHubService gitHubService;
    @Mock
    private UserRepository userRepository;
    
    @Test
    void shouldIncrementCounter(){
        //given
        final var userDto = new UserDto(1L, exampleLogin, "name", "user", URI.create("https://test.test"),
                Instant.now(), 1.5);
        when(gitHubService.getUser(exampleLogin)).thenReturn(userDto);

        // when
        new GitHubFacade(userRepository, gitHubService).getUser(exampleLogin);
        
        // then
        verify(gitHubService, times(1)).getUser(exampleLogin);
        verify(userRepository, times(1)).existsByLoginIgnoreCase(exampleLogin);
        verify(userRepository, times(1)).incrementCounter(exampleLogin);
    }

}
