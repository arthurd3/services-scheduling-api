package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToResponse.serviceToResponse;

@Service
public class FindServiceByName {

    private final ServiceRepository serviceRepository;

    public FindServiceByName(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    public Optional<ServiceResponseDTO> findService(String name){
        var findService = serviceRepository.findByName(name)
                .orElseThrow(() -> new ServiceNotFoundException("Servico com nome " + name + " não encontrado."));

        return Optional.of(serviceToResponse(findService));

    }


}
