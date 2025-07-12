package com.arthur.schedulingApi.usecases.service.mapper;

import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.models.service.Service;

import java.util.List;

import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToResponse.serviceToResponse;

public class ServiceListToResponse {

    public static List<ServiceResponseDTO> serviceListToResponse(List<Service> serviceList) {
        return serviceList
                .stream()
                .map(ServiceToResponse::serviceToResponse)
                .toList();
    }
}
