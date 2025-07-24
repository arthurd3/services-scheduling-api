package com.arthur.schedulingApi.payment.stripe.usecases;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.enums.SchedulingPaymentStatus;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import com.arthur.schedulingApi.usecases.EmailService;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmPaymentUseCase {

    private final SchedulingRepository schedulingRepository;
    private final EmailService emailService;

    @Transactional
    public void confirmPayment(PaymentIntent paymentIntent) {
        String schedulingIdStr = paymentIntent.getMetadata().get("scheduling_id");
        if (schedulingIdStr == null) {
            log.error("Webhook de pagamento recebido sem scheduling_id nos metadados. PaymentIntent ID: {}", paymentIntent.getId());
            return;
        }

        Long schedulingId = Long.parseLong(schedulingIdStr);
        Scheduling scheduling = schedulingRepository.findById(schedulingId)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado: " + schedulingId));

        if (scheduling.getPaymentStatus() != SchedulingPaymentStatus.PAYMENT_PENDING) {
            log.warn("Pagamento confirmado para um agendamento que não estava PENDING_PAYMENT. Status atual: {}. Scheduling ID: {}",
                    scheduling.getStatus(), scheduling.getId());
            return;
        }

        scheduling.setStatus(SchedulingStatus.BOOKED);
        scheduling.setStripePaymentIntentId(paymentIntent.getId());
        scheduling.setPaymentDueDate(null);

        emailService.sendConfirmationEmail(
                scheduling.getClient().getEmail(),
                scheduling.getClient().getName(),
                scheduling.getServices().getName(),
                scheduling.getStartTime(),
                scheduling.getServices().getLocation()
        );
    }
}