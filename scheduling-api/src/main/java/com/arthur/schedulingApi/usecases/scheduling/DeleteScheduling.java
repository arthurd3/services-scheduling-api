package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;

public class DeleteScheduling {

    private final SchedulingRepository schedulingRepository;

    public DeleteScheduling(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    public void deleteScheduling(Long idScheduling) {
        if(!schedulingRepository.existsById(idScheduling)) {
            throw new SchedulingNotFoundException("Nao foi Possivel deletar o agendamento com id " + idScheduling + "nao existe!");
        }
        schedulingRepository.deleteById(idScheduling);
    }
}
