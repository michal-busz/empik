package it.busz.empik.data; //TODO consider moving to another package

import it.busz.empik.data.model.UserDto;
import it.busz.empik.data.model.UserExternalDto;

public class UserMapper { //TODO consider removing and using Apace Commons BeanUtils

    private UserMapper(){
    }

    public static UserDto map(UserExternalDto user, Double calculations){
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
