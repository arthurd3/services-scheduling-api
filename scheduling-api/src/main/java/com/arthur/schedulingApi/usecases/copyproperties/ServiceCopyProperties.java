package com.arthur.schedulingApi.usecases.copyproperties;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.service.Services;

import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedListValue;
import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedValue;
import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingListToModel.schedulingListToModel;

public class ServiceCopyProperties {

    public static Services copyServiceProperties(ServiceRequestDTO serviceRequestDTO , Services services) {

        var serviceRequestList = schedulingListToModel(serviceRequestDTO.schedulingList(), services);

        services.setName(getUpdatedValue(serviceRequestDTO.name(), services.getName()));
        services.setCapacity(getUpdatedValue(serviceRequestDTO.capacity(), services.getCapacity()));
        services.setDescription(getUpdatedValue(serviceRequestDTO.description(), services.getDescription()));
        services.setLocation(getUpdatedValue(serviceRequestDTO.location(), services.getLocation()));
        services.setUrl_image(getUpdatedValue(serviceRequestDTO.url_image(), services.getUrl_image()));
        services.setScheduling(getUpdatedListValue(serviceRequestList, services.getScheduling()));

        return services;
    }








}
