package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.Services;

import java.util.ArrayList;

public class ServiceToModel {

    public static Services serviceToModel(ServiceRequestDTO serviceRequestDTO) {

        return new Services(
                serviceRequestDTO.name(),
                serviceRequestDTO.capacity(),
                serviceRequestDTO.description(),
                serviceRequestDTO.location(),
                serviceRequestDTO.url_image(),
                new ArrayList<>()
        );

    }

}
