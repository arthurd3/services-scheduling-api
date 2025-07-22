package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.SchedulingToModel.schedulingToModel;
import static com.arthur.schedulingApi.usecases.mapper.SchedulingToResponse.schedulingToResponse;

@Service
@RequiredArgsConstructor
public class CreateScheduling {

    private final FindServiceById findServiceById;
    private final SchedulingRepository schedulingRepository;

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