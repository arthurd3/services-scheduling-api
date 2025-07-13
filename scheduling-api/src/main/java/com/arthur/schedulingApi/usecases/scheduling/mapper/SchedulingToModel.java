package com.arthur.schedulingApi.usecases.scheduling.mapper;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingRequestDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;


public class SchedulingToModel {

    public static Scheduling schedulingToModel(SchedulingRequestDTO schedulingRequestDTO) {

        return new Scheduling(
                schedulingRequestDTO.id(),
                schedulingRequestDTO.startTime(),
                schedulingRequestDTO.endTime(),
                schedulingRequestDTO.status()
        );

    }



}
