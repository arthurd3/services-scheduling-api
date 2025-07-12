package com.arthur.schedulingApi.usecases.user.mapper;

import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.models.user.User;

import static com.arthur.schedulingApi.usecases.service.mapper.ServiceListToResponse.serviceListToResponse;

public class UserMapperToResponse {

    public static UserResponseDTO userToResponse(User userModel) {

        var serviceList = serviceListToResponse(userModel.getServicesOwned());

        return new UserResponseDTO(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getPhoneNumber(),
                userModel.getRole().toString(),
                userModel.getSchedulings(),
                serviceList
        );

    }
}
