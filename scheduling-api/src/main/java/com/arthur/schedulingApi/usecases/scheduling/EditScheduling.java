package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse.schedulingToResponse;

@Service
public class EditScheduling {

    private final FindScheduling findScheduling;

    public EditScheduling(FindScheduling findScheduling) {
        this.findScheduling = findScheduling;
    }

    @Transactional
    public SchedulingResponseDTO editSchedulingTime(Long id , SchedulingSlotRequestDTO schedulingSlotRequestDTO) {
        var schedulingOriginal = findScheduling.findSchedulingAsModel(id);

        if (schedulingSlotRequestDTO.startTime() != null) {
            schedulingOriginal.setStartTime(schedulingSlotRequestDTO.startTime());
        }
        if (schedulingSlotRequestDTO.endTime() != null) {
            schedulingOriginal.setEndTime(schedulingSlotRequestDTO.endTime());
        }

        return schedulingToResponse(schedulingOriginal);
    }
}
