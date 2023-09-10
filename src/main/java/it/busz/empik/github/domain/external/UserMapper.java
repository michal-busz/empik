package it.busz.empik.github.domain.external;

import it.busz.empik.github.infrastructure.api.UserDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
class UserMapper {

    public static UserDto externalToUserDto(UserExternalDto user, Double calculations) {
        return new UserDto(
                user.id(),
                user.login(),
                user.name(),
                user.type(),
                user.avatarUrl(),
                user.createdAt(),
                calculations
        );
    }

}
