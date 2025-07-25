package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.arthur.schedulingApi.usecases.mapper.SchedulingToModel.schedulingToModel;

public class SchedulingListToModel {

    public static List<Scheduling> schedulingListToModel(List<SchedulingSlotRequestDTO> schedulingList , Services services) {

        if(schedulingList == null || schedulingList.isEmpty()) {
            return new ArrayList<>();
        }

        return schedulingList.stream()
                .map(schedulingRequestDTO ->  schedulingToModel(schedulingRequestDTO, services))
                .collect(Collectors.toList());
    }
}
