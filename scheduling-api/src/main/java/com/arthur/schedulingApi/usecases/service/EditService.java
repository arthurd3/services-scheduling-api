package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;

import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.usecases.copyproperties.ServiceCopyProperties.copyServiceProperties;
import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToResponse.serviceToResponse;

@Service
public class EditService {

    private final ServiceRepository serviceRepository;

    public EditService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Transactional
    public Optional<ServiceResponseDTO> editService(Long id ,ServiceRequestDTO serviceRequestDTO) {
        var originalService = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Nao e Possivel Editar Servico nao Encontrado"));

        var editedService = copyServiceProperties(serviceRequestDTO , originalService);
        serviceRepository.save(editedService);
        return Optional.of(serviceToResponse(editedService));
    }



}
