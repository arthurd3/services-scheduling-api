package com.arthur.schedulingApi.usecases.scheduling.mapper;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;

import java.util.List;
import java.util.stream.Collectors;

public class SchedulingListToRequest {

    public static List<SchedulingSlotRequestDTO> schedulingListToRequest (List<Scheduling> schedulingList) {

        return schedulingList.stream().map(scheduling -> new SchedulingSlotRequestDTO(
                scheduling.getEndTime() , scheduling.getStartTime()
                )).collect(Collectors.toList());
    }
}

