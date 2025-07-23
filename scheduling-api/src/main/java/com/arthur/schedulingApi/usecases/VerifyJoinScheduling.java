package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.SchedulingNotAvailableException;
import com.arthur.schedulingApi.exceptions.UnauthorizedException;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VerifyJoinScheduling {

    public void verifyJoin(User client , Scheduling scheduling){
        verifySchedulingIsAvailable(scheduling);
        verifyClientIsNotServiceOwner(client , scheduling);
    }

    private void verifySchedulingIsAvailable(Scheduling scheduling) {
        if (scheduling.getStatus() != SchedulingStatus.AVAILABLE) {
            throw new SchedulingNotAvailableException("Este horário não está mais disponível.");
        }
    }

    private void verifyClientIsNotServiceOwner(User client , Scheduling scheduling){
        Long serviceId = scheduling.getServices().getId();

        boolean isOwner = client.getServicesOwned().stream()
                .anyMatch(service -> service.getId().equals(serviceId));

        if (isOwner) {
            throw new UnauthorizedException("Você não pode agendar no seu próprio serviço.");
        }
    }

}
