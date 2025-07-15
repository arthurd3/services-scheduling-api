package com.arthur.schedulingApi.usecases.scheduling.mapper;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.service.Services;


public class SchedulingToModel {


    public static Scheduling schedulingToModel(SchedulingSlotRequestDTO schedulingSlotRequestDTO, Services service) {

        return new Scheduling(
                schedulingSlotRequestDTO.startTime(),
                schedulingSlotRequestDTO.endTime(),
                service
        );
    }
}
