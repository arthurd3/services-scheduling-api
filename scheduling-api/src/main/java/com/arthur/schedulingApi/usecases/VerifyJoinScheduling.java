package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.SchedulingNotAvailableException;
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
            log.warn("Tentativa de agendamento em horário indisponível. Scheduling ID: {}, Status: {}",
                    scheduling.getId(), scheduling.getStatus());
            throw new SchedulingNotAvailableException("Este horário não está mais disponível.");
        }
    }

    private void verifyClientIsNotServiceOwner(User client , Scheduling scheduling){
        Long serviceId = scheduling.getServices().getId();

        boolean isOwner = client.getServicesOwned().stream()
                .anyMatch(service -> service.getId().equals(serviceId));

        if (isOwner) {
            log.warn("Cliente tentou agendar no próprio serviço. Client ID: {}, Service ID: {}",
                    client.getId(), serviceId);
            throw new SchedulingNotAvailableException("Você não pode agendar no seu próprio serviço.");
        }
    }

}
