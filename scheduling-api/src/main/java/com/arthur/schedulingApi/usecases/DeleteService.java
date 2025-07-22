package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final ServiceRepository serviceRepository;

    public void deleteById(Long id) {

        if (!serviceRepository.existsById(id)) {
            throw new ServiceNotFoundException("Não é possível deletar: Serviço com id " + id + " não encontrado.");
        }

        serviceRepository.deleteById(id);
    }
}