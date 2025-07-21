package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.request.UpdateStatusRequestDTO;
import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
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
    public SchedulingResponseDTO changeStatus(UpdateStatusRequestDTO status , Long idScheduling) {

        Scheduling schedulingToUpdate = findScheduling.findSchedulingAsModel(idScheduling);

        SchedulingStatus updateStatus = status.newStatus();

        schedulingToUpdate.setStatus(updateStatus);

        return schedulingToResponse(schedulingToUpdate);
    }
}
