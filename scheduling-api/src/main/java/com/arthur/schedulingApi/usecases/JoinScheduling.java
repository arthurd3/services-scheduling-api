package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.utilities.verify.VerifyJoinScheduling;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import static com.arthur.schedulingApi.usecases.mapper.SchedulingToResponse.schedulingToResponse;

@Service
@RequiredArgsConstructor
public class JoinScheduling {

    private final AuthenticatedUserService authenticatedUserService;
    private final FindScheduling findScheduling;
    private final VerifyJoinScheduling verifyJoinScheduling;

    @Transactional
    public SchedulingResponseDTO joinScheduling(Long id) {

        var client = authenticatedUserService.getAuthenticatedUser();

        var scheduling = findScheduling.findSchedulingAsModel(id);

        verifyJoinScheduling.verifyJoin(client , scheduling);

        scheduling.setClient(client);
        scheduling.setStatus(SchedulingStatus.BOOKED);

        return schedulingToResponse(scheduling);
    }
}
