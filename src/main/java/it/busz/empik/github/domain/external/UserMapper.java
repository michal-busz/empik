package it.busz.empik.github.domain.external;

import it.busz.empik.github.infrastructure.api.UserDto;

class UserMapper {

    private UserMapper() {
    }

    public static UserDto map(UserExternalDto user, Double calculations) {
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
