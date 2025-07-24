package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.enums.SchedulingPaymentStatus;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.mapper.SchedulingToResponse;
import com.arthur.schedulingApi.utilities.verify.VerifyJoinScheduling;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.arthur.schedulingApi.usecases.mapper.SchedulingToResponse.schedulingToResponse;

@Service
@RequiredArgsConstructor
public class JoinScheduling {

    private final AuthenticatedUserService authenticatedUserService;
    private final FindScheduling findScheduling;
    private final VerifyJoinScheduling verifyJoinScheduling;
    private final FindUser findUser;
    private final EmailService emailService;

    @Transactional
    public SchedulingResponseDTO joinScheduling(Long schedulingId) {

        var client = findUser.findByIdAsModel(authenticatedUserService.getAuthenticatedUser().getId());

        var scheduling = findScheduling.findSchedulingAsModel(schedulingId);

        verifyJoinScheduling.verifyJoin(client , scheduling);

        scheduling.setClient(client);
        scheduling.setStatus(SchedulingStatus.BOOKED);
        scheduling.setPaymentStatus(SchedulingPaymentStatus.PAYMENT_PENDING);
        scheduling.setPaymentDueDate(LocalDateTime.now().plusHours(24));

        emailService.sendReservationConfirmationEmail(scheduling);

        return schedulingToResponse(scheduling);
    }
}
