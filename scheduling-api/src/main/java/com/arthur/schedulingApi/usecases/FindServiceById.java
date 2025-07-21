package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.ServiceToResponse.serviceToResponse;

@Service
public class FindServiceById {

    private final ServiceRepository serviceRepository;

    public FindServiceById(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ServiceResponseDTO findById(Long id){
        var findService = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Servico com id "+ id +" nao encontrado"));
        return serviceToResponse(findService);
    }

    public Services findByIdAsModel(Long id){
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Servico com id "+ id +" nao encontrado"));
    }

}
