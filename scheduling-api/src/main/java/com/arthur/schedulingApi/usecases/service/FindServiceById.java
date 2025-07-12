package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToResponse.serviceToResponse;

@Service
public class FindServiceById {

    private final ServiceRepository serviceRepository;

    public FindServiceById(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Optional<ServiceResponseDTO> findById(Long id){
        var findedService = serviceRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException("Servico com id "+ id +" nao encontrado"));
        return Optional.of(serviceToResponse(findedService));
    }
}
