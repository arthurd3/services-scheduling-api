package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.ServiceToResponse.serviceToResponse;

@Service
public class FindServiceByName {

    private final ServiceRepository serviceRepository;

    public FindServiceByName(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    public ServiceResponseDTO findService(String name){
        var findService = serviceRepository.findByName(name)
                .orElseThrow(() -> new ServiceNotFoundException("Servico com nome " + name + " não encontrado."));

        return serviceToResponse(findService);

    }


}
