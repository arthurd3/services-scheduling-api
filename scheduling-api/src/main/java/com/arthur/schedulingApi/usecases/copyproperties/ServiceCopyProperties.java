package com.arthur.schedulingApi.usecases.copyproperties;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.service.Services;

import java.util.List;

import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedListValue;
import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedValue;
import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingListToModel.schedulingListToModel;

public class ServiceCopyProperties {

    public static Services copyServiceProperties(ServiceRequestDTO serviceRequestDTO , Services serviceToUpdate) {

        serviceToUpdate.setName(getUpdatedValue(serviceRequestDTO.name(), serviceToUpdate.getName()));
        serviceToUpdate.setCapacity(getUpdatedValue(serviceRequestDTO.capacity(), serviceToUpdate.getCapacity()));
        serviceToUpdate.setDescription(getUpdatedValue(serviceRequestDTO.description(), serviceToUpdate.getDescription()));
        serviceToUpdate.setLocation(getUpdatedValue(serviceRequestDTO.location(), serviceToUpdate.getLocation()));
        serviceToUpdate.setUrl_image(getUpdatedValue(serviceRequestDTO.url_image(), serviceToUpdate.getUrl_image()));

        if (serviceRequestDTO.schedulingList() != null) {

            List<Scheduling> existingSchedulings = serviceToUpdate.getScheduling();

            List<Scheduling> newSchedulings = schedulingListToModel(serviceRequestDTO.schedulingList(), serviceToUpdate);

            existingSchedulings.clear();

            existingSchedulings.addAll(newSchedulings);
        }

        return serviceToUpdate;
    }








}
