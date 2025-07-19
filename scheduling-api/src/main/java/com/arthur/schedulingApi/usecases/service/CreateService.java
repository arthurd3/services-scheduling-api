package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import com.arthur.schedulingApi.security.userAuth.AuthenticatedUserService;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToModel.serviceToModel;
import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToResponse.serviceToResponse;

@Service
public class CreateService {

    private final ServiceRepository serviceRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public CreateService(ServiceRepository serviceRepository, AuthenticatedUserService authenticatedUserService) {
        this.serviceRepository = serviceRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    public ServiceResponseDTO createService(ServiceRequestDTO serviceRequestDTO) {

        User ownerUser = authenticatedUserService.getAuthenticatedUser();

        var serviceModel = serviceToModel(serviceRequestDTO);
        serviceModel.setOwner(ownerUser);

        var savedService = serviceRepository.save(serviceModel);

        return serviceToResponse(savedService);
    }
}
