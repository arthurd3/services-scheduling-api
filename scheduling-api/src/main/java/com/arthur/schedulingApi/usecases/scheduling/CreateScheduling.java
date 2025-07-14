package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;
import com.arthur.schedulingApi.usecases.service.FindServiceById;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Optional<SchedulingResponseDTO> createScheduling(SchedulingRequestDTO schedulingRequestDTO) {

        Services service = findServiceById.findByIdAsModel(schedulingRequestDTO.serviceId());

        Scheduling scheduling = schedulingToModel(schedulingRequestDTO , service);

        var schedulingReturn = schedulingRepository.save(scheduling);
        service.addScheduling(scheduling);

        return Optional.of(schedulingToResponse(schedulingReturn));

    }



}
