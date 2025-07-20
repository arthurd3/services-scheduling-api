package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;
import com.arthur.schedulingApi.security.userAuth.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FindUserScheduling {
    private final SchedulingRepository schedulingRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public FindUserScheduling(SchedulingRepository schedulingRepository, AuthenticatedUserService authenticatedUserService) {
        this.schedulingRepository = schedulingRepository;

        this.authenticatedUserService = authenticatedUserService;
    }

    public Page<SchedulingResponseDTO> findUserScheduling( Integer page) {

        Long userId = authenticatedUserService.getAuthenticatedUser().getId();

        Pageable pageable = PageRequest.of(page, 10);
        Page<Scheduling> schedulingPage = schedulingRepository.findByClientId(userId, pageable);

        return schedulingPage
                .map(SchedulingToResponse::schedulingToResponse);
    }

}
