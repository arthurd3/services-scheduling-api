package com.arthur.schedulingApi.usecases.copyproperties;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToModel;


import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedListValue;
import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedValue;
import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToModel.schedulingToModel;


public class ServiceCopyProperties {

    public static Services copyServiceProperties(ServiceRequestDTO serviceRequestDTO , Services services) {

        var serviceRequestList = serviceRequestDTO.schedulingList().stream()
                .map(scheduling -> {
                    return schedulingToModel(scheduling, services);
                })
                .toList();

        var editedService = new Services(
                services.getId(),
                getUpdatedValue(serviceRequestDTO.name() , services.getName()),
                getUpdatedValue(serviceRequestDTO.capacity() , services.getCapacity()),
                getUpdatedValue(serviceRequestDTO.description() , services.getDescription()),
                getUpdatedValue(serviceRequestDTO.location() , services.getLocation()),
                getUpdatedValue(serviceRequestDTO.url_image() , services.getUrl_image()),
                getUpdatedListValue( serviceRequestList, services.getScheduling())
        );

        editedService.setOwner(services.getOwner());
        editedService.setCreatedAt(services.getCreatedAt());
        return editedService;
    }








}
