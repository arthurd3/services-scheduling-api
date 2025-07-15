package com.arthur.schedulingApi.usecases.user.mapper;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.models.user.UserRoles;


public class UserMapperToModel {

    public static User userToModel(UserRequestDTO userRequestDTO) {

        return new User(
            userRequestDTO.name(),
            userRequestDTO.email(),
            userRequestDTO.password(),
            userRequestDTO.phoneNumber()
        );
    }
}
