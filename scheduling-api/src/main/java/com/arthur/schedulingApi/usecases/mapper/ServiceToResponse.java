package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.models.Services;

import static com.arthur.schedulingApi.usecases.mapper.UserMapperToResume.userToResume;


public class ServiceToResponse {

    public static ServiceResponseDTO serviceToResponse(Services services) {

        var schedulingList = services.getScheduling().stream().map(SchedulingToResponse::schedulingToResponse).toList();

        return new ServiceResponseDTO(
                services.getId(),
                services.getName(),
                userToResume(services.getOwner()),
                services.getCapacity(),
                services.getDescription(),
                services.getLocation(),
                services.getUrl_image(),
                schedulingList,
                services.getCreatedAt()
        );
    }

}
