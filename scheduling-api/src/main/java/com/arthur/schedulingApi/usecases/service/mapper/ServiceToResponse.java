package com.arthur.schedulingApi.usecases.service.mapper;

import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;

import com.arthur.schedulingApi.models.service.Service;

public class ServiceToResponse {

    public static ServiceResponseDTO serviceToResponse(Service service) {
        return new ServiceResponseDTO(
                service.getName(),
                service.getOwnerId(),
                service.getCapacity(),
                service.getDescription(),
                service.getLocation(),
                service.getUrl_image(),
                service.getScheduling(),
                service.getCreatedAt()
        );
    }

}
