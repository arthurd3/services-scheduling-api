package com.arthur.schedulingApi.utilities.copyproperties;

import com.arthur.schedulingApi.controllers.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.Services;

import java.util.List;

import static com.arthur.schedulingApi.utilities.copyproperties.GetUpdateValue.getUpdatedValue;
import static com.arthur.schedulingApi.usecases.mapper.SchedulingListToModel.schedulingListToModel;

public class ServiceCopyProperties {

    public static Services copyServiceProperties(ServiceRequestDTO serviceRequestDTO , Services serviceToUpdate) {

        serviceToUpdate.setName(getUpdatedValue(serviceRequestDTO.name(), serviceToUpdate.getName()));
        serviceToUpdate.setCapacity(getUpdatedValue(serviceRequestDTO.capacity(), serviceToUpdate.getCapacity()));
        serviceToUpdate.setDescription(getUpdatedValue(serviceRequestDTO.description(), serviceToUpdate.getDescription()));
        serviceToUpdate.setLocation(getUpdatedValue(serviceRequestDTO.location(), serviceToUpdate.getLocation()));
        serviceToUpdate.setUrl_image(getUpdatedValue(serviceRequestDTO.url_image(), serviceToUpdate.getUrl_image()));

        if (serviceRequestDTO.schedulingList() != null) {

            List<Scheduling> existingScheduling = serviceToUpdate.getScheduling();
            List<Scheduling> newScheduling = schedulingListToModel(serviceRequestDTO.schedulingList(), serviceToUpdate);
            existingScheduling.clear();
            existingScheduling.addAll(newScheduling);

        }

        return serviceToUpdate;
    }








}
