package com.arthur.schedulingApi.controllers.response;

import com.arthur.schedulingApi.models.enums.SchedulingStatus;

import java.time.LocalDateTime;

public record SchedulingResponseDTO(
        Long id,
        SchedulingStatus status,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ServiceInfo service,
        ClientInfo client
) {

    public record ServiceInfo(
            Long id,
            String name,
            String location
    ) {}


    public record ClientInfo(
            Long id,
            String name
    ) {}
}
