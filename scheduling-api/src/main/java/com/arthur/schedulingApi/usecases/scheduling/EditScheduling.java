package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse.schedulingToResponse;
import static com.arthur.schedulingApi.utilities.copyproperties.SchedulingCopyProperties.copyProperties;

@Service
public class EditScheduling {

    private final FindScheduling findScheduling;

    public EditScheduling(FindScheduling findScheduling) {
        this.findScheduling = findScheduling;
    }

    @Transactional
    public SchedulingResponseDTO editScheduling(Long id , SchedulingSlotRequestDTO schedulingSlotRequestDTO) {
        var schedulingOriginal = findScheduling.findSchedulingAsModel(id);
        copyProperties(schedulingOriginal, schedulingSlotRequestDTO);
        return schedulingToResponse(schedulingOriginal);
    }
}
