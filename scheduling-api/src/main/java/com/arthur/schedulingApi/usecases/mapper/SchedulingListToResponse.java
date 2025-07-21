package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.Scheduling;

import java.util.List;
import java.util.stream.Collectors;

public class SchedulingListToResponse {

    public static List<SchedulingResponseDTO> schedulingListToResponse(List<Scheduling> schedulingList) {
        return schedulingList.stream()
                .map(SchedulingToResponse::schedulingToResponse)
                .collect(Collectors.toList());
    }
}
