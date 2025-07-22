package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.mapper.SchedulingToResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserScheduling {

    private final SchedulingRepository schedulingRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public Page<SchedulingResponseDTO> findUserScheduling(Pageable pageable) {

        Long userId = authenticatedUserService.getAuthenticatedUser().getId();

        Page<Scheduling> schedulingPage = schedulingRepository.findByClientId(userId, pageable);

        return schedulingPage
                .map(SchedulingToResponse::schedulingToResponse);
    }
}