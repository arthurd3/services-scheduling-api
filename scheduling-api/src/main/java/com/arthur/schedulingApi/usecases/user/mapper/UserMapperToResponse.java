package com.arthur.schedulingApi.usecases.user.mapper;

import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.models.user.User;

public class UserMapperToResponse {

    public UserResponseDTO UserToResponse(User userModel) {

        return new UserResponseDTO(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getPhoneNumber()
        );

    }
}
