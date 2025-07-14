package com.arthur.schedulingApi.usecases.scheduling.mapper;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingRequestDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.service.Services;

import java.util.List;
import java.util.stream.Collectors;

import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToModel.schedulingToModel;

public class SchedulingListToModel {

    public static List<Scheduling> schedulingListToModel(List<SchedulingRequestDTO> schedulingList , Services services) {

        return schedulingList.stream()
                .map(schedulingRequestDTO ->  schedulingToModel(schedulingRequestDTO, services))
                .collect(Collectors.toList());
    }
}
