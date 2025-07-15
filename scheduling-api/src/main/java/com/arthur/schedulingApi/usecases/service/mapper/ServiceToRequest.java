package com.arthur.schedulingApi.usecases.service.mapper;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.service.Services;

import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingListToRequest.schedulingListToRequest;

public class ServiceToRequest {

    public static ServiceRequestDTO serviceToRequest(Services service) {
        return new ServiceRequestDTO(
                service.getName(),
                service.getCapacity(),
                service.getUrl_image(),
                service.getDescription(),
                service.getLocation(),
                schedulingListToRequest(service.getScheduling())
        );
    }

}
