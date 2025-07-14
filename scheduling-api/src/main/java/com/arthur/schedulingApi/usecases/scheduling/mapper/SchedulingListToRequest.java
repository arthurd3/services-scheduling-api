package com.arthur.schedulingApi.usecases.scheduling.mapper;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingRequestDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;

import java.util.List;
import java.util.stream.Collectors;

public class SchedulingListToRequest {

    public static List<SchedulingRequestDTO> schedulingListToRequest (List<Scheduling> schedulingList) {

        return schedulingList.stream().map(scheduling -> new SchedulingRequestDTO(scheduling.getId() ,
                scheduling.getServices().getOwner().getId() ,
                scheduling.getServices().getId() ,
                scheduling.getStatus() , scheduling.getEndTime() ,
                scheduling.getStartTime())
        ).collect(Collectors.toList());
    }
}

