package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.ServiceToModel.serviceToModel;
import static com.arthur.schedulingApi.usecases.mapper.ServiceToResponse.serviceToResponse;

@Service
@RequiredArgsConstructor
public class CreateService {

    private final ServiceRepository serviceRepository;
    private final AuthenticatedUserService authenticatedUserService;

    @CachePut(value = "SERVICE_CACHE" , key = "#result.id()")
    public ServiceResponseDTO createService(ServiceRequestDTO serviceRequestDTO) {

        User ownerUser = authenticatedUserService.getAuthenticatedUser();

        var serviceModel = serviceToModel(serviceRequestDTO);
        serviceModel.setOwner(ownerUser);

        var savedService = serviceRepository.save(serviceModel);

        return serviceToResponse(savedService);
    }
}