package com.arthur.schedulingApi.usecases.copyproperties;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.service.Services;

import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedListValue;
import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedValue;
import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingListToModel.schedulingListToModel;

public class ServiceCopyProperties {

    public static Services copyServiceProperties(ServiceRequestDTO serviceRequestDTO , Services services) {

        var serviceRequestList = schedulingListToModel(serviceRequestDTO.schedulingList() , services);

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
