package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.SchedulingNotAvailableException;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import org.springframework.stereotype.Component;

@Component
public class VerifyJoinScheduling {

    public void verifyJoin(User client , Scheduling scheduling){
        if (scheduling.getStatus() != SchedulingStatus.AVAILABLE) {
            throw new SchedulingNotAvailableException("Este horário não está mais disponível.");
        }
        var schedulingService = scheduling.getServices().getId();

        if(client.getServicesOwned().stream().anyMatch(services -> services.getId().equals(schedulingService))) {
            throw new SchedulingNotAvailableException("Voce nao pode agendar no seu proprio servico.");
        };
    }

}
