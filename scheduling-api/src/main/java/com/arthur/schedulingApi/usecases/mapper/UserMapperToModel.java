package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.request.UserRequestDTO;
import com.arthur.schedulingApi.models.User;


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
