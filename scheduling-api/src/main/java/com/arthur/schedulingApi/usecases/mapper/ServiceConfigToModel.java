package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.request.ServiceConfigRequestDTO;
import com.arthur.schedulingApi.models.ServiceConfiguration;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfigToModel {


    public static ServiceConfiguration convertConfigToModel(ServiceConfigRequestDTO dto) {
        return ServiceConfiguration.builder()
                .autoGenerationEnabled(dto.getAutoGenerationEnabled())
                .slotDurationInMinutes(dto.getSlotDurationInMinutes())
                .workStartTime(dto.getWorkStartTime())
                .workEndTime(dto.getWorkEndTime())
                .autoGenerationInSaturdays(dto.getAutoGenerationInSaturdays())
                .autoGenerationInSundays(dto.getAutoGenerationInSundays())
                .lunchStartTime(dto.getLunchStartTime())
                .lunchDurationInMinutes(dto.getLunchDurationInMinutes())
                .startEarlyInWeekends(dto.getStartEarlyInWeekends())
                .endEarlyInWeekends(dto.getEndEarlyInWeekends())
                .build();
    }

    public static ServiceConfiguration updateModel(ServiceConfiguration entity, ServiceConfigRequestDTO dto) {

        entity.setAutoGenerationEnabled(dto.getAutoGenerationEnabled());
        entity.setSlotDurationInMinutes(dto.getSlotDurationInMinutes());
        entity.setWorkStartTime(dto.getWorkStartTime());
        entity.setWorkEndTime(dto.getWorkEndTime());
        entity.setAutoGenerationInSaturdays(dto.getAutoGenerationInSaturdays());
        entity.setAutoGenerationInSundays(dto.getAutoGenerationInSundays());
        entity.setLunchStartTime(dto.getLunchStartTime());
        entity.setLunchDurationInMinutes(dto.getLunchDurationInMinutes());
        entity.setStartEarlyInWeekends(dto.getStartEarlyInWeekends());
        entity.setEndEarlyInWeekends(dto.getEndEarlyInWeekends());

        return entity;
    }
}