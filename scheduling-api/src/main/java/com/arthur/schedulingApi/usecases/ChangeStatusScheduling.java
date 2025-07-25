package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.UpdateStatusRequestDTO;
import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.SchedulingToResponse.schedulingToResponse;

@Service
@RequiredArgsConstructor
public class ChangeStatusScheduling {

    private final FindScheduling findScheduling;

    @Transactional
    public SchedulingResponseDTO changeStatus(UpdateStatusRequestDTO status , Long idScheduling) {

        Scheduling schedulingToUpdate = findScheduling.findSchedulingAsModel(idScheduling);

        SchedulingStatus updateStatus = status.newStatus();

        schedulingToUpdate.setStatus(updateStatus);

        return schedulingToResponse(schedulingToUpdate);
    }
}