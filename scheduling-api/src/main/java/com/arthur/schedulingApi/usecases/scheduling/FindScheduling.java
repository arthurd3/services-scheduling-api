package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse.schedulingToResponse;

@Service
public class FindScheduling {

    private final SchedulingRepository schedulingRepository;

    public FindScheduling(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    public SchedulingResponseDTO findScheduling(Long id) {
        var schedulingReturn = schedulingRepository.findById(id)
                .orElseThrow(() -> new SchedulingNotFoundException("Agendamento com id: "+ id +" Nao encontrado!!"));

        return schedulingToResponse(schedulingReturn);
    }

    public Scheduling findSchedulingAsModel(Long id) {
        return schedulingRepository.findById(id)
                .orElseThrow(() -> new SchedulingNotFoundException("Agendamento com id: "+ id +" Nao encontrado!!"));
    }
}
