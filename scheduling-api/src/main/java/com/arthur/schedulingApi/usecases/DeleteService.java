package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.utilities.verify.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final ServiceRepository serviceRepository;
    private final AuthenticatedUserService  authenticatedUser;
    private final VerifyService verify;

    @CacheEvict(value = "SERVICE_CACHE", key = "#id")
    public void deleteById(Long id) {

        User authUser = authenticatedUser.getAuthenticatedUser();

        var foundedService = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Não é possível deletar: Serviço com id " + id + " não encontrado."));

        verify.verifyDelete(authUser , foundedService);

        serviceRepository.deleteById(id);
    }
}