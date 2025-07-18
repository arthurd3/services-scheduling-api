package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteById {

    private final ServiceRepository serviceRepository;

    public DeleteById(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void deleteById(Long id) {

        if (!serviceRepository.existsById(id)) {
            throw new ServiceNotFoundException("Não é possível deletar: Serviço com id " + id + " não encontrado.");
        }

        serviceRepository.deleteById(id);
    }

}
