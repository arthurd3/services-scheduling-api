package com.arthur.schedulingApi.utilities.tasks;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.enums.SchedulingPaymentStatus;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CancelExpiredReservationsTask {

    private final SchedulingRepository schedulingRepository;

    @Scheduled(fixedRate = 300000)
    @Transactional
    public void cancelExpired() {
        log.info("Iniciando tarefa de cancelamento de reservas expiradas...");

        List<Scheduling> expired = schedulingRepository.findAllByPaymentStatusAndPaymentDueDateBefore(
                SchedulingPaymentStatus.PAYMENT_PENDING,
                LocalDateTime.now()
        );

        if (expired.isEmpty()) {
            log.info("Nenhuma reserva expirada encontrada.");
            return;
        }

        log.info("Encontradas {} reservas expiradas para cancelar.", expired.size());

        for (Scheduling scheduling : expired) {
            scheduling.setStatus(SchedulingStatus.AVAILABLE);
            scheduling.setClient(null);
            scheduling.setPaymentDueDate(null);
            scheduling.setStripePaymentIntentId(null);
            scheduling.setPaymentStatus(null);
        }

        schedulingRepository.saveAll(expired);
        log.info("{} reservas expiradas foram canceladas com sucesso.", expired.size());
    }
}