package com.arthur.schedulingApi.usecases.service.mapper;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.service.Service;

import java.util.ArrayList;

public class ServiceToModel {

    public static Service serviceToModel(ServiceRequestDTO serviceRequestDTO) {

        return new Service(
                serviceRequestDTO.id(),
                serviceRequestDTO.name(),
                serviceRequestDTO.capacity(),
                serviceRequestDTO.description(),
                serviceRequestDTO.location(),
                serviceRequestDTO.url_image(),
                new ArrayList<>()
        );

    }

}
