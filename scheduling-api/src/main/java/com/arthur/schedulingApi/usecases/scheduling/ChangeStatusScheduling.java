package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.models.scheduling.SchedulingStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse.schedulingToResponse;

@Service
public class ChangeStatusScheduling {
    private final FindScheduling findScheduling;

    public ChangeStatusScheduling(FindScheduling findScheduling) {
        this.findScheduling = findScheduling;
    }

    @Transactional
    public SchedulingResponseDTO changeStatus(SchedulingStatus status , Long idScheduling) {
        for(SchedulingStatus schedulingStatus : SchedulingStatus.values()) {
            if(schedulingStatus.equals(status)) {
                var returnedScheduling = findScheduling.findSchedulingAsModel(idScheduling);
                returnedScheduling.setStatus(schedulingStatus);
                return schedulingToResponse(returnedScheduling);
            }
        }
        throw new SchedulingNotFoundException("Status nao Encontrado!!!");
    }
}
