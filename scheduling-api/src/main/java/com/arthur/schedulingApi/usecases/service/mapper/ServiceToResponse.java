package com.arthur.schedulingApi.usecases.service.mapper;

import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;

import com.arthur.schedulingApi.models.service.Service;

import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResume.userToResume;


public class ServiceToResponse {

    public static ServiceResponseDTO serviceToResponse(Service service) {

        return new ServiceResponseDTO(
                service.getId(),
                service.getName(),
                userToResume(service.getOwner()),
                service.getCapacity(),
                service.getDescription(),
                service.getLocation(),
                service.getUrl_image(),
                service.getScheduling(),
                service.getCreatedAt()
        );
    }

}
