package com.arthur.schedulingApi.utilities.copyproperties;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;

import static com.arthur.schedulingApi.utilities.copyproperties.GetUpdateValue.getUpdatedValue;

public class SchedulingCopyProperties {


    public static Scheduling copyProperties(Scheduling schedulingToUpdate , SchedulingSlotRequestDTO schedulingSlotRequestDTO) {

        schedulingToUpdate.setStartTime(getUpdatedValue(schedulingSlotRequestDTO.startTime(), schedulingToUpdate.getStartTime()));
        schedulingToUpdate.setEndTime(getUpdatedValue(schedulingSlotRequestDTO.endTime(), schedulingToUpdate.getEndTime()));

        return schedulingToUpdate;
    }
}
