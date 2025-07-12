package com.arthur.schedulingApi.usecases.copyproperties;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.service.Service;

import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedValue;

public class ServiceCopyProperties {

    public static Service copyServiceProperties(ServiceRequestDTO serviceRequestDTO , Service service) {
        var editedService = new Service(
                service.getId(),
                getUpdatedValue(serviceRequestDTO.name() , service.getName()),
                getUpdatedValue(serviceRequestDTO.capacity() , service.getCapacity()),
                getUpdatedValue(serviceRequestDTO.description() , service.getDescription()),
                getUpdatedValue(serviceRequestDTO.location() , service.getLocation()),
                getUpdatedValue(serviceRequestDTO.url_image() , service.getUrl_image()),
                service.getScheduling()
        );

        editedService.setOwner(service.getOwner());
        editedService.setCreatedAt(service.getCreatedAt());
        return editedService;
    }








}
