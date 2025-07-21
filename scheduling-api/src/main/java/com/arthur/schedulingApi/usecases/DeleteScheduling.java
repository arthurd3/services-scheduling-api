package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteScheduling {

    private final SchedulingRepository schedulingRepository;

    public DeleteScheduling(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    public void deleteScheduling(Long idScheduling) {
        if(!schedulingRepository.existsById(idScheduling)) {
            throw new SchedulingNotFoundException("Nao foi Possivel deletar o agendamento com id " + idScheduling + " nao existe!!");
        }
        schedulingRepository.deleteById(idScheduling);
    }
}
