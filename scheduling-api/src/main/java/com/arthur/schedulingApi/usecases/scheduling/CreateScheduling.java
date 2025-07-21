package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.scheduling.SchedulingStatus;
import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;
import com.arthur.schedulingApi.usecases.service.FindServiceById;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToModel.schedulingToModel;
import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse.schedulingToResponse;

@Service
public class CreateScheduling {

    private final FindServiceById findServiceById;
    private final SchedulingRepository schedulingRepository;

    public CreateScheduling(FindServiceById findServiceById, SchedulingRepository schedulingRepository) {
        this.findServiceById = findServiceById;
        this.schedulingRepository = schedulingRepository;
    }

    @Transactional
    public SchedulingResponseDTO createScheduling(SchedulingSlotRequestDTO schedulingSlotRequestDTO, Long serviceId) {

        Services service = findServiceById.findByIdAsModel(serviceId);

        Scheduling scheduling = schedulingToModel(schedulingSlotRequestDTO, service);

        scheduling.setStatus(SchedulingStatus.AVAILABLE);

        var schedulingReturn = schedulingRepository.save(scheduling);

        service.addScheduling(scheduling);

        return schedulingToResponse(schedulingReturn);

    }



}
