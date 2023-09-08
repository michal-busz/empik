package it.busz.empik.github.domain.external;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.time.Instant;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GitHubServiceTest {
    private static final String exampleLogin = "login";
    @Mock
    private GitHubClient gitHubClient;

    @Test
    void shouldCalculateForPositiveFollowers() {
        // given
        final var externalUser = new UserExternalDto(1L, exampleLogin, "name", "user", URI.create("https://test.test"),
                Instant.now(), 12L, 1L);
        when(gitHubClient.getUser(exampleLogin)).thenReturn(externalUser);
        // when
        final var user = new GitHubServiceImpl(gitHubClient).getUser(exampleLogin);

        // then
        verify(gitHubClient, times(1)).getUser(exampleLogin);
        assert user.calculations() == 1.5;
    }

    @Test
    void shouldCalculateForZeroFollowers() {
        // given
        final var externalUser = new UserExternalDto(1L, exampleLogin, "name", "user", URI.create("https://test.test"),
                Instant.now(), 0L, 1L);
        when(gitHubClient.getUser(exampleLogin)).thenReturn(externalUser);
        // when
        final var user = new GitHubServiceImpl(gitHubClient).getUser(exampleLogin);

        // then
        verify(gitHubClient, times(1)).getUser(exampleLogin);
        assert Double.isInfinite(user.calculations());
    }
}
