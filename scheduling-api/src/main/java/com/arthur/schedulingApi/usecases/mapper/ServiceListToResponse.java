package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.models.Services;

import java.util.List;

public class ServiceListToResponse {

    public static List<ServiceResponseDTO> serviceListToResponse(List<Services> servicesList) {
        return servicesList
                .stream()
                .map(ServiceToResponse::serviceToResponse)
                .toList();
    }
}
