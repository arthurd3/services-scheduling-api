package com.arthur.schedulingApi.usecases.scheduling.mapper;

import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;

public class SchedulingToResponse {

    public static SchedulingResponseDTO schedulingToResponse(Scheduling scheduling) {

        var serviceInfo = new SchedulingResponseDTO.ServiceInfo(
                scheduling.getServices().getId(),
                scheduling.getServices().getName(),
                scheduling.getServices().getLocation()
        );


        SchedulingResponseDTO.ClientInfo clientInfo = null;
        if (scheduling.getClient() != null) {
            clientInfo = new SchedulingResponseDTO.ClientInfo(
                    scheduling.getClient().getId(),
                    scheduling.getClient().getName()
            );
        }

        return new SchedulingResponseDTO(
                scheduling.getId(),
                scheduling.getStatus(),
                scheduling.getStartTime(),
                scheduling.getEndTime(),
                serviceInfo,
                clientInfo
        );
    }
}
