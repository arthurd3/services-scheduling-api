package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.SchedulingToResponse.schedulingToResponse;

@Service
@RequiredArgsConstructor
public class FindScheduling {

    private final SchedulingRepository schedulingRepository;

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