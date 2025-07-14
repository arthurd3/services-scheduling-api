package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;
import com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FindUserScheduling {
    private final SchedulingRepository schedulingRepository;

    public FindUserScheduling(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;

    }

    public List<SchedulingResponseDTO> findUserScheduling(Long userId , Pageable pageable) {
        Page<Scheduling> schedulingPage = schedulingRepository.findByClientId(userId, pageable);

        return schedulingPage
                .map(SchedulingToResponse::schedulingToResponse).toList();
    }

}
