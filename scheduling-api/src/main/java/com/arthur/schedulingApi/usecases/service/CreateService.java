package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import com.arthur.schedulingApi.usecases.user.FindUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToModel.serviceToModel;
import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToResponse.serviceToResponse;

@Service
public class CreateService {

    private final ServiceRepository serviceRepository;
    private final FindUser findUser;

    public CreateService(ServiceRepository serviceRepository, FindUser findUser) {
        this.serviceRepository = serviceRepository;

        this.findUser = findUser;
    }

    public Optional<ServiceResponseDTO> createService(Long ownerId, ServiceRequestDTO serviceRequestDTO) {

        User ownerUser = findUser.findUserEntity(ownerId);

        var serviceModel = serviceToModel(serviceRequestDTO);
        serviceModel.setOwner(ownerUser);

        var savedService = serviceRepository.save(serviceModel);

        return Optional.of(serviceToResponse(savedService));
    }
}
