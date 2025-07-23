package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.utilities.verify.VerifyScheduling;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.SchedulingToResponse.schedulingToResponse;

@Service
@RequiredArgsConstructor
public class EditScheduling {

    private final FindScheduling findScheduling;
    private final AuthenticatedUserService authenticatedUserService;
    private final VerifyScheduling verifyEdit;

    @Transactional
    public SchedulingResponseDTO editSchedulingTime(Long id , SchedulingSlotRequestDTO schedulingSlotRequestDTO) {

        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();

        var schedulingOriginal = findScheduling.findSchedulingAsModel(id);

        verifyEdit.verifyEdit(authenticatedUser , schedulingOriginal);

        if (schedulingSlotRequestDTO.startTime() != null) {
            schedulingOriginal.setStartTime(schedulingSlotRequestDTO.startTime());
        }
        if (schedulingSlotRequestDTO.endTime() != null) {
            schedulingOriginal.setEndTime(schedulingSlotRequestDTO.endTime());
        }

        return schedulingToResponse(schedulingOriginal);
    }
}