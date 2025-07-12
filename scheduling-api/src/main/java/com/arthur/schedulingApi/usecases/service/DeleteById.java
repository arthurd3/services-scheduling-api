package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteById {

    private final ServiceRepository serviceRepository;

    public DeleteById(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public boolean deleteById(Long id) {
        var findedService = serviceRepository.findById(id);

        if(findedService.isPresent()){
            serviceRepository.delete(findedService.get());
            return true;
        }
        return false;
    }

}
