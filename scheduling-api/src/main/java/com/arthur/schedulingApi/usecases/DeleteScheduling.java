package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.utilities.verify.VerifyScheduling;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteScheduling {

    private final SchedulingRepository schedulingRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final VerifyScheduling verify;

    public void deleteScheduling(Long idScheduling) {

        User authUser = authenticatedUserService.getAuthenticatedUser();

        verify.verifyDelete(authUser ,  idScheduling);

        if(!schedulingRepository.existsById(idScheduling)) {
            throw new SchedulingNotFoundException("Nao foi Possivel deletar o agendamento com id " + idScheduling + " nao existe!!");
        }
        schedulingRepository.deleteById(idScheduling);
    }
}