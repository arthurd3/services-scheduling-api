package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.request.ServiceConfigRequestDTO;
import com.arthur.schedulingApi.models.ServiceConfiguration;
import com.arthur.schedulingApi.models.Services;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfigToModel {


    public static ServiceConfiguration convertConfigToModel(ServiceConfigRequestDTO serviceConfiguration) {
        return ServiceConfiguration.builder()
                .autoGenerationEnabled(serviceConfiguration.getAutoGenerationEnabled())
                .slotDurationInMinutes(serviceConfiguration.getSlotDurationInMinutes())
                .workStartTime(serviceConfiguration.getWorkStartTime())
                .workEndTime(serviceConfiguration.getWorkEndTime())
                .build();
    }
}