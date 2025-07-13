package com.arthur.schedulingApi.usecases.scheduling.mapper;

import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;

public class SchedulingToResponse {

    public static SchedulingResponseDTO schedulingToResponse(Scheduling scheduling) {

        return new SchedulingResponseDTO(
                scheduling.getId(),
                scheduling.getStatus(),
                scheduling.getStartTime(),
                scheduling.getEndTime()
        );
    }
}
