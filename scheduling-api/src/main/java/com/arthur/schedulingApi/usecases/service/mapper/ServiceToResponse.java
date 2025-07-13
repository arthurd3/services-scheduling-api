package com.arthur.schedulingApi.usecases.service.mapper;

import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;

import com.arthur.schedulingApi.models.service.Services;

import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResume.userToResume;


public class ServiceToResponse {

    public static ServiceResponseDTO serviceToResponse(Services services) {

        return new ServiceResponseDTO(
                services.getId(),
                services.getName(),
                userToResume(services.getOwner()),
                services.getCapacity(),
                services.getDescription(),
                services.getLocation(),
                services.getUrl_image(),
                services.getScheduling(),
                services.getCreatedAt()
        );
    }

}
