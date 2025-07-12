package com.arthur.schedulingApi.usecases.service.mapper;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.service.Service;

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



    private static <T> T getUpdatedValue(T newValue, T oldValue) {

        if (newValue == null) {
            return oldValue;
        }

        if (newValue instanceof String) {
            if (((String) newValue).isBlank()) {
                return oldValue;
            }
        }

        return newValue;
    }





}
