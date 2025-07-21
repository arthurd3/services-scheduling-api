package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.models.User;

import static com.arthur.schedulingApi.usecases.mapper.SchedulingListToResponse.schedulingListToResponse;
import static com.arthur.schedulingApi.usecases.mapper.ServiceListToResponse.serviceListToResponse;

public class UserMapperToResponse {

    public static UserResponseDTO userToResponse(User userModel) {

        var serviceList = serviceListToResponse(userModel.getServicesOwned());
        var schedulingList = schedulingListToResponse(userModel.getSchedulings());
        return new UserResponseDTO(
                userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPhoneNumber(),
                userModel.getRole().toString(),
                schedulingList,
                serviceList,
                userModel.updateScore(),
                userModel.getRatingsReceivedSize()
        );
    }
}
