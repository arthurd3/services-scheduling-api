package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.Services;


public class SchedulingToModel {


    public static Scheduling schedulingToModel(SchedulingSlotRequestDTO schedulingSlotRequestDTO, Services service) {

        return new Scheduling(
                schedulingSlotRequestDTO.startTime(),
                schedulingSlotRequestDTO.endTime(),
                service
        );
    }
}
