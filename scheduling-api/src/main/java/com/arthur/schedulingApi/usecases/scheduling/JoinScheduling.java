package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.models.scheduling.SchedulingStatus;
import com.arthur.schedulingApi.security.userAuth.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.user.FindUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse.schedulingToResponse;

@Service
public class JoinScheduling {

    private final AuthenticatedUserService authenticatedUserService;
    private final FindScheduling findScheduling;

    public JoinScheduling(AuthenticatedUserService authenticatedUserService, FindScheduling findScheduling) {
        this.authenticatedUserService = authenticatedUserService;

        this.findScheduling = findScheduling;
    }

    @Transactional
    public SchedulingResponseDTO joinScheduling(Long id) {

        var client = authenticatedUserService.getAuthenticatedUser();
        var scheduling = findScheduling.findSchedulingAsModel(id);

        if (scheduling.getStatus() != SchedulingStatus.AVAILABLE) {
            throw new SchedulingNotFoundException("Este horário não está mais disponível.");
        }

        scheduling.setClient(client);
        scheduling.setStatus(SchedulingStatus.BOOKED);
        client.addScheduling(scheduling);

        return schedulingToResponse(scheduling);
    }
}
