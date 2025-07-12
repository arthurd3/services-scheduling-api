package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToModel.serviceToModel;
import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToResponse.serviceToResponse;

@Service
public class CreateService {
    private final ServiceRepository serviceRepository;

    public CreateService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Optional<ServiceResponseDTO> createService(Long ownerId , ServiceRequestDTO serviceRequestDTO) {

        var serviceModel = serviceToModel(serviceRequestDTO);
        serviceModel.setOwnerId(ownerId);

        serviceRepository.save(serviceModel);

        return Optional.of(serviceToResponse(serviceModel));
    }
}
