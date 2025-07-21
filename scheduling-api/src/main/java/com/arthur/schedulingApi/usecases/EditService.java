package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.utilities.copyproperties.ServiceCopyProperties.copyServiceProperties;
import static com.arthur.schedulingApi.usecases.mapper.ServiceToResponse.serviceToResponse;

@Service
public class EditService {

    private final FindServiceById findServiceById;

    public EditService(FindServiceById findServiceById) {
        this.findServiceById = findServiceById;
    }


    @Transactional
    public ServiceResponseDTO editService(Long id ,ServiceRequestDTO serviceRequestDTO) {

        var originalService = findServiceById.findByIdAsModel(id);

        var editedService = copyServiceProperties(serviceRequestDTO , originalService);

        return serviceToResponse(editedService);
    }



}
