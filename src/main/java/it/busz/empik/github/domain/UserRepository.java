package it.busz.empik.github.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("update User u set u.requestCount = u.requestCount + 1 where upper(u.login) = upper(?1)")
    void updateCounter(@NonNull String login);

    boolean existsByLoginIgnoreCase(@NonNull String login);
}
