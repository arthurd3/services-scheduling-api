package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.utilities.copyproperties.ServiceCopyProperties.copyServiceProperties;
import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToResponse.serviceToResponse;

@Service
public class EditService {

    private final FindServiceById findServiceById;

    public EditService(FindServiceById findServiceById) {
        this.findServiceById = findServiceById;
    }


    @Transactional
    public Optional<ServiceResponseDTO> editService(Long id ,ServiceRequestDTO serviceRequestDTO) {

        var originalService = findServiceById.findByIdAsModel(id);

        var editedService = copyServiceProperties(serviceRequestDTO , originalService);

        return Optional.of(serviceToResponse(editedService));
    }



}
