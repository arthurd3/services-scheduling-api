package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.SchedulingToResponse.schedulingToResponse;

@Service
@RequiredArgsConstructor
public class EditScheduling {

    private final FindScheduling findScheduling;

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